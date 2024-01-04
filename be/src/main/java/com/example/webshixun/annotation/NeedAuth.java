package com.example.webshixun.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD,ElementType.FIELD})
public @interface NeedAuth {

//    public boolean needAuth() default false;

    public String[] needRole() default {};
}
