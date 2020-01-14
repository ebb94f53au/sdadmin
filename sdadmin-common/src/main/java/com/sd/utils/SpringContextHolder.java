package com.sd.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author siyang
 * @create 2020-01-12 21:33
 * 用于不在spring管辖范围获得ioc容器中的对象
 */
@Slf4j
@Component
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext =null;


    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     * @param clazz
     * @return
     */
    public static<T> T getBean(Class<T> clazz){
        assertContextInjected();
        return (T)applicationContext.getBean(clazz);

    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     * @param beanName
     * @return
     */
    public static<T> T getBean(String beanName){

        assertContextInjected();
        return (T) applicationContext.getBean(beanName);

    }
    /**
     * 用于检验applicationContext是否为空
     */
    private static void assertContextInjected(){
        if(applicationContext==null){
            throw new IllegalStateException("applicaitonContext属性未注入, 请在applicationContext" +
                    ".xml中定义SpringContextHolder或在SpringBoot启动类中注册SpringContextHolder.");
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringContextHolder.applicationContext != null) {
            log.warn("SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:" + SpringContextHolder.applicationContext);
        }
        SpringContextHolder.applicationContext = applicationContext;

    }
    /**
     * 清除SpringContextHolder中的ApplicationContext为Null.
     */
    private static void clearHolder() {
        log.debug("清除SpringContextHolder中的ApplicationContext:"
                + applicationContext);
        applicationContext = null;
    }
    /**
     * 对象销毁时会调用此方法 需要在spring的依赖下
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        clearHolder();
    }
}
