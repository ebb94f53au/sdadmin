package com.sd.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author siyang
 * @create 2020-01-13 15:41
    用于标记匿名访问方法
 *  判断方法上是否有此注释，如果标有无视权限放行
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnonymousAccess {
}
