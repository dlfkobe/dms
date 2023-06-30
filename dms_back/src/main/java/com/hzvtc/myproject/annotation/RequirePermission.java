package com.hzvtc.myproject.annotation;

import com.hzvtc.myproject.utils.Match;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 
 * @date 2023-06-18
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequirePermission {
    /**
     * 权限标识
     * @return
     */
    String[] permissions();

    /**
     * 匹配方式
     * @return
     */
    Match matchType() default Match.HAS_ANY;
}
