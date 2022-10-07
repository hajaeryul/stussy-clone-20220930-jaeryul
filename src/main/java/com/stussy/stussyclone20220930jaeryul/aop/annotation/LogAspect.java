package com.stussy.stussyclone20220930jaeryul.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //프로그램 실행 중에 쓸거다
@Target({ElementType.TYPE, ElementType.METHOD}) // 클래스 위, 메소드 위에다가 어노테이션 달아도 된다
public @interface LogAspect {
}
