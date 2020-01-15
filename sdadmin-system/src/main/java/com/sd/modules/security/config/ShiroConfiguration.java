package com.sd.modules.security.config;

import cn.hutool.crypto.digest.BCrypt;
import com.sd.annotation.AnonymousAccess;
import com.sd.modules.security.security.JwtTokenProvider;
import com.sd.modules.security.shiro.DbShiroRealm;
import com.sd.modules.security.shiro.JwtAuthFilter;
import com.sd.modules.security.shiro.JwtShiroRealm;
import com.sd.modules.security.shiro.JwtToken;
import com.sd.modules.system.service.UserService;
import com.sd.utils.SpringContextHolder;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSessionStorageEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author siyang
 * @create 2020-01-12 16:13
 * shiro配置类
 */

@Configuration
public class ShiroConfiguration {
//    @Autowired
//    private RequestMappingHandlerMapping resourceHandlerMapping;
//    @Autowired
//    private SpringContextHolder springContextHolder;

    /**
     * 设置过滤器
     * @param securityManager
     * @return
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager, JwtTokenProvider jwtTokenProvider){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //配置安全管理
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        filters.put("authcToken", createAuthFilter(jwtTokenProvider));
//        filters.put("anyRole", createRolesFilter());
        shiroFilterFactoryBean.setFilters(filters);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition().getFilterChainMap());

        return shiroFilterFactoryBean;

    }

    /**
     * 配置拦截路径及相应过滤器
     * @return
     */
    @Bean
    protected ShiroFilterChainDefinition shiroFilterChainDefinition() {
//        Map<RequestMappingInfo, HandlerMethod> handlerMethods = resourceHandlerMapping.getHandlerMethods();
//        Map<String, String> anonyMap = new HashMap<>();
//        handlerMethods.forEach((key, value) -> {
//            if(value.hasMethodAnnotation(AnonymousAccess.class)){
//                Set<String> patterns = key.getPatternsCondition().getPatterns();
//                anonyMap.put(key.getPatternsCondition().getPatterns().toString(),"noSessionCreation,anon");
//            }
//
//        });
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
//        chainDefinition.addPathDefinitions(anonyMap);
        chainDefinition.addPathDefinition("/*.html", "anon");
        chainDefinition.addPathDefinition("/**/*.html", "anon");
        chainDefinition.addPathDefinition("/**/*.css", "anon");
        chainDefinition.addPathDefinition("/**/*.js", "anon");
        chainDefinition.addPathDefinition("/webSocket/**", "anon");
        chainDefinition.addPathDefinition("/swagger-ui.html", "anon");
        chainDefinition.addPathDefinition("/swagger-resources/**", "anon");
        chainDefinition.addPathDefinition("/webjars/**", "anon");
        chainDefinition.addPathDefinition("/*/api-docs", "anon");
        chainDefinition.addPathDefinition("/avatar/**", "anon");
        chainDefinition.addPathDefinition("/file/**", "anon");
        chainDefinition.addPathDefinition("/druid/**", "anon");
        chainDefinition.addPathDefinition("/avatar/**", "anon");
        //控制器路径
        chainDefinition.addPathDefinition("/", "anon");
        chainDefinition.addPathDefinition("/auth/login", "anon");
        chainDefinition.addPathDefinition("/auth/code", "anon");
        chainDefinition.addPathDefinition("/auth/logout", "authcToken[permissive]");

        chainDefinition.addPathDefinition("/**", "authcToken");  //对所有进行验证token
//        chainDefinition.addPathDefinition("/**", "noSessionCreation,authcToken"); // 默认进行用户鉴权
        return chainDefinition;
    }

    /**
     * 禁用session, 不保存用户登录状态。保证每次请求都重新认证。
     * 需要注意的是，如果用户代码里调用Subject.getSession()还是可以用session，如果要完全禁用，要配合下面的noSessionCreation的Filter来实现
     */
    @Bean
    protected SessionStorageEvaluator sessionStorageEvaluator(){
        DefaultWebSessionStorageEvaluator sessionStorageEvaluator = new DefaultWebSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        return sessionStorageEvaluator;
    }

    /**
     * 注册shiro的Filter，拦截请求
     */
    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean(SecurityManager securityManager, JwtTokenProvider jwtTokenProvider) throws Exception{
        FilterRegistrationBean<Filter> filterRegistration = new FilterRegistrationBean<Filter>();
        filterRegistration.setFilter((Filter)shiroFilter(securityManager, jwtTokenProvider).getObject());
        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
        filterRegistration.setAsyncSupported(true);
        filterRegistration.setEnabled(true);
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);

        return filterRegistration;
    }
    /**
     * 初始化Authenticator
     */
    @Bean
    public Authenticator authenticator() {
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        //设置两个Realm，一个用于用户登录验证和访问权限获取；一个用于jwt token的认证
        authenticator.setRealms(Arrays.asList(dbShiroRealm(),jwtShiroRealm()));
        //设置多个realm认证策略，一个成功即跳过其它的
        authenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());
        return authenticator;
    }

    /**
     * DbRealm,默认的密码校验算法为BCrypt
     * @return
     */
    @Bean("dbRealm")
    public Realm dbShiroRealm() {
        DbShiroRealm myShiroRealm = new DbShiroRealm();
        //将Realm的默认密码校验设置为BCrypt算法
        myShiroRealm.setCredentialsMatcher(new CredentialsMatcher() {
            @Override
            public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
                String password = new String(((UsernamePasswordToken) authenticationToken).getPassword());
                String hashed = (String) authenticationInfo.getCredentials();
                return BCrypt.checkpw(password,hashed);
            }
        });
        return myShiroRealm;
    }

    /**
     * jwtToken->Realm
     * 校验前后token是否相同，其实可以直接返回true。
     * 因为前面过滤器已经验证过token的完整性和正确性
     * @return
     */
    @Bean("jwtRealm")
    public Realm jwtShiroRealm() {
        JwtShiroRealm myShiroRealm = new JwtShiroRealm();
        myShiroRealm.setCredentialsMatcher(new CredentialsMatcher() {
            @Override
            public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
                JwtToken jwtToken1 = (JwtToken) authenticationToken;
                JwtToken jwtToken2 = (JwtToken)authenticationInfo.getCredentials();
                String token1 = jwtToken1.getToken();
                String token2 = jwtToken2.getToken();

                return token1.equals(token2);
            }
        });
        return myShiroRealm;
    }

    /**
     * jwtToken过滤器
     * @return
     */
    protected JwtAuthFilter createAuthFilter(JwtTokenProvider jwtTokenProvider){
        return new JwtAuthFilter(jwtTokenProvider);
    }
}
