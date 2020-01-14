package com.sd.modules.security.shiro;

import com.sd.modules.security.config.SecurityProperties;
import com.sd.modules.security.security.JwtTokenProvider;
import com.sd.modules.system.service.UserService;
import com.sd.utils.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author siyang
 * @create 2020-01-12 20:40
 * jwt过滤器
 */
@Slf4j
public class JwtAuthFilter extends BasicHttpAuthenticationFilter {

    private JwtTokenProvider jwtTokenProvider;


    public JwtAuthFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     *父类会在请求进入拦截器后调用该方法，返回true则继续，返回false则会调用onAccessDenied()。这里在不通过时，还调用了isPermissive()方法，我们后面解释。
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //如果已经登录，直接放行
        if(this.isLoginRequest(request,response)){
            return true;
        }
        boolean allowed = false;
        try {
            //内部的方法会执行createToken
            allowed = executeLogin(request, response);
        } catch(IllegalStateException e){ //not found any token
            log.error("Not found any token");
        }catch (Exception e) {
            log.error("Error occurs when login", e);
        }
        return allowed || super.isPermissive(mappedValue);
    }

    /**
     *  这里重写了父类的方法，使用我们自己定义的Token类，提交给shiro。这个方法返回null的话会直接抛出异常，进入isAccessAllowed（）的异常处理逻辑。
     * @param request
     * @param response
     * @return
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        //从request头中取出token
        String token = jwtTokenProvider.getToken((HttpServletRequest) request);
        //如果token不为空 并且token验证合格
        if(token!=null&&jwtTokenProvider.vaildateToken(token)){
            return new JwtToken(token);
        }
        return null;
    }

    /**
     * 如果这个Filter在之前isAccessAllowed（）方法中返回false,则会进入这个方法。我们这里直接返回错误的response
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json;charset=UTF-8");
        httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(),"Token Error");
        return false;
    }
}
