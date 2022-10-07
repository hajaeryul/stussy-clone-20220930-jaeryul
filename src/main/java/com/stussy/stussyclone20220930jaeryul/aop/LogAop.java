package com.stussy.stussyclone20220930jaeryul.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAop {

    @Pointcut("execution(* com.stussy.stussyclone20220930jaeryul.api.*Api.*(..))") //매개변수가 있는 경우만 찍힘. 매개변수 없는 경우도 실행하겠따 할때는 마지막 (..)를 (*)로
    private void pointCut() {}

    @Pointcut("@annotation(com.stussy.stussyclone20220930jaeryul.aop.annotation.LogAspect)")
    private void annotationPointCut() {}

        @Around("annotationPointCut()")
        public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

            CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature(); //코드시그니쳐로 다운캐스팅
            String className = codeSignature.getDeclaringTypeName(); //클래스명
            String methodName = codeSignature.getName(); //메소드명
            String[] parameterNames = codeSignature.getParameterNames(); //매개변수 이름 들고오기
            Object[] args = joinPoint.getArgs(); //매개변수 값 들고오기

            for(int i = 0; i < parameterNames.length; i++) {
                log.info("<<<< Parameter info >>>> {}.{} >>> [{}: {}]", className, methodName, parameterNames[i], args[i]);
            }

            Object result = joinPoint.proceed();

            log.info("<<<< Return >>>> {}.{} >>> [{}]", className, methodName, result);

            return result;
    }

}
