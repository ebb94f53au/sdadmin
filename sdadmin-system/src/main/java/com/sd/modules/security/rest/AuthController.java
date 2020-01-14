package com.sd.modules.security.rest;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.sd.annotation.AnonymousAccess;
import com.sd.exception.BadRequestException;
import com.sd.modules.security.config.SecurityProperties;
import com.sd.modules.security.security.JwtTokenProvider;
import com.sd.modules.security.security.vo.AuthUser;
import com.sd.modules.security.security.vo.JwtUser;
import com.sd.modules.security.security.vo.OnlineUser;
import com.sd.modules.security.service.JwtUserService;
import com.sd.modules.security.service.OnlineUserService;
import com.sd.modules.system.domain.User;
import com.sd.modules.system.service.UserService;
import com.sd.utils.RedisUtils;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author siyang
 * @create 2020-01-12 14:23
 * 鉴定权限
 */
@RestController
@RequestMapping("/auth")
@Api(tags="系统：系统授权接口")
public class AuthController {

    @Value("${loginCode.expiration}")
    private Long expiration;//验证码有效时间
    @Value("${rsa.private_key}")
    private String privateKey;//非对称性加密私匙
    @Value("${single.login:false}")
    private Boolean singleLogin;//单用户登录 默认为false

    @Autowired
    private SecurityProperties properties;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    JwtUserService jwtUserService;
    @Autowired
    OnlineUserService onlineUserService;
    @Autowired
    UserService userService;
    @ApiOperation("登录授权")
    @PostMapping("/login")
    public ResponseEntity<Object> login(@Validated @RequestBody AuthUser authUser, HttpServletRequest request) throws BadRequestException {
        Subject subject = SecurityUtils.getSubject();
        //密码解密,前端发过来的密码会进行非对称性加密
        RSA rsa = new RSA(privateKey,null);
        byte[] decrypt = rsa.decrypt(authUser.getPassword(), KeyType.PrivateKey);
        String pwd = new String(decrypt);
        //检查验证码
        String uuid = authUser.getUuid();
        String code = (String)redisUtils.get(uuid);
        //删除验证码
        redisUtils.del(uuid);

        if(StringUtils.isBlank(code)){
            throw new BadRequestException("验证码不存在或者过期");
        }
        if(StringUtils.isBlank(authUser.getCode())||!StringUtils.equalsIgnoreCase(code,authUser.getCode())){
            throw new BadRequestException("验证码不正确");
        }
        //将验证交由shiro进行
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(authUser.getUsername(), pwd);
        subject.login(usernamePasswordToken);

        //1.生成token
        String token = jwtTokenProvider.createToken(authUser.getUsername());
        //2.保存在线信息
        User user = (User)subject.getPrincipal();
        JwtUser jwtUser = jwtUserService.createJwtUser(user);
        onlineUserService.save(jwtUser,token,request);

        Map<String,Object> authInfo = new HashMap<String,Object>(2){{
            put("token", properties.getTokenStartWith() + token);
            put("user", jwtUser);
        }};
        //singleLogin为true代表默认开启单号登录，其他角色会被挤下来
        if(singleLogin){

        }

        return ResponseEntity.ok(authInfo);
    }
    @ApiOperation("获取用户信息")
    @GetMapping(value = "/info")
    public ResponseEntity<Object> getUserInfo(HttpServletRequest request){
        String token = jwtTokenProvider.getToken(request);
        String username = jwtTokenProvider.parseToken(token);
        User user = userService.getUserByUsername(username);
        JwtUser jwtUser = (JwtUser)jwtUserService.createJwtUser(user);
        return ResponseEntity.ok(jwtUser);
    }

    @ApiOperation("获取验证码")
    @GetMapping("/code")
    public ResponseEntity<Object> getCode(){
        // 使用第三方验证码包 https://gitee.com/whvse/EasyCaptcha
        //使用静态验证码
        SpecCaptcha captcha = new SpecCaptcha(111,36,4);
        //获取验证的结果
        String result = captcha.text();
        String uuid = properties.getCodeKey() + IdUtil.simpleUUID();
        //将验证码的结果存入redis中，并且设置存在时间为2分钟
        redisUtils.set(uuid,result,expiration, TimeUnit.MINUTES);

        Map<String, Object> imgMap = new HashMap<String, Object>(2) {{
            put("img",captcha.toBase64());
            put("uuid",uuid);
        }};
        return ResponseEntity.ok(imgMap);

    }
    @ApiOperation("退出登录")
    @DeleteMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request){
        //删除redis中的key
        onlineUserService.logout(jwtTokenProvider.getToken(request));
        //shiro也登出，shiro 无状态不需要
//        SecurityUtils.getSubject().logout();
        return new ResponseEntity(HttpStatus.OK);
    }

}
