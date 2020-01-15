package com.sd.modules.security.shiro;

import com.sd.exception.BadRequestException;
import com.sd.modules.security.security.vo.JwtUser;
import com.sd.modules.security.service.JwtUserService;
import com.sd.modules.system.domain.User;
import com.sd.modules.system.service.UserService;
import com.sd.modules.system.service.dto.UserDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author siyang
 * @create 2020-01-12 20:15
 */
public class DbShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUserService jwtUserService;


    /**
     * 限定这个Realm只支持我们自定义的JWT Token
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole("111");
        simpleAuthorizationInfo.addStringPermission("sss:aaa");
        return null;
    }

    /**
     * 验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken =(UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        UserDto userDto = null;
        try {
            userDto= userService.getUserByUsername(username);
        } catch (BadRequestException e) {
            //将异常向上抛出
            throw e;

        }
        //构建jwt -》VO对象
        JwtUser jwtUser = jwtUserService.createJwtUser(userDto);

        return new SimpleAuthenticationInfo(jwtUser,userDto.getPassword(),"dbRealm");
    }
}
