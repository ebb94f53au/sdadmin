package com.sd.modules.security.service;

import com.sd.modules.security.config.SecurityProperties;
import com.sd.modules.security.security.vo.JwtUser;
import com.sd.modules.security.security.vo.OnlineUser;
import com.sd.modules.system.domain.User;
import com.sd.utils.EncryptUtils;
import com.sd.utils.RedisUtils;
import com.sd.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author siyang
 * @create 2020-01-13 14:40
 * 在线用户服务类。操作redis
 */
@Slf4j
@Service
public class OnlineUserService {
    @Autowired
    SecurityProperties properties;
    @Autowired
    RedisUtils redisUtils;

    /**
     * 将JwtUser转化为OnlineUser并保存下来
     * 保存在线用户服务
     * @param jwtUser
     * @param token
     * @param request
     * @throws Exception
     */
    public void save(JwtUser jwtUser, String token, HttpServletRequest request) {

        OnlineUser onlineUser= null;
        String browser= StringUtils.getBrowser(request);
        String ip = StringUtils.getIp(request);
        String address = StringUtils.getCityInfo(ip);
        String job = jwtUser.getDept() + "/" + jwtUser.getJob();
        try {
            onlineUser = new OnlineUser(jwtUser.getUsername(),jwtUser.getNickName(),job,browser,ip,address, EncryptUtils.desEncrypt(token),new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }

        redisUtils.set(properties.getOnlineKey()+token,onlineUser,properties.getTokenValidityInSeconds()/1000);

    }

    /**
     * 用户下线
     */
    public void logout(String token){
        redisUtils.del(properties.getOnlineKey()+token);
    }

}
