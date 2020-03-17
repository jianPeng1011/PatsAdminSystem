package com.example.pethospitalmanagement.entity;

import java.lang.annotation.*;

/**
 * 需要登录才能进行操作的注解UserLoginToken
 */

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserLoginToken {
    boolean requirea() default true;
}
