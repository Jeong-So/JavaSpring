package com.example.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD}) // method에 사용
@Retention(RetentionPolicy.RUNTIME)  // runtime에 돌아감
public @interface Timer {
}
