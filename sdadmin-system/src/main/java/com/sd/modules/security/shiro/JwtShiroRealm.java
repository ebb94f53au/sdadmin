package com.sd.modules.security.shiro;

import com.sd.exception.BadRequestException;
import com.sd.modules.security.security.JwtTokenProvider;
import com.sd.modules.system.domain.User;
import com.sd.modules.system.service.UserService;
import com.sd.modules.system.service.dto.UserDto;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author siyang
 * @create 2020-01-14 18:13
 */
public class JwtShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    /**
     * 限定这个Realm只支持我们自定义的JWT Token
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }



    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * token登录，每次请求都要判断路径
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken token = (JwtToken) authenticationToken;
        String username = jwtTokenProvider.parseToken(token.getToken());

        try {
            userService.getUserByUsername(username);
        } catch (BadRequestException e) {
            throw e;
        }

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(token,token,"jwtRealm");
        return simpleAuthenticationInfo;
    }
}
