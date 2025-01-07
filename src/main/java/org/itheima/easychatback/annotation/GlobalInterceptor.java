package org.itheima.easychatback.annotation;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)

public @interface GlobalInterceptor {

    boolean checkLogin()default true;

    boolean checkAdmin()default false;



}
