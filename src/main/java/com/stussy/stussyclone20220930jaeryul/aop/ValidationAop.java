package com.stussy.stussyclone20220930jaeryul.aop;

import com.stussy.stussyclone20220930jaeryul.exception.CustomValidationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Aspect
@Component
public class ValidationAop {

    @Pointcut("execution(* com.stussy.stussyclone20220930jaeryul..*Api.*(..))") //패키지는 .. 클래스 메소드 * 매개변수는 (..)
    private void executionPointCut() {} //포인트컷은 어라운드 안에 써도 되지만 밖에 꺼내주자 !

    @Around("executionPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();//getArgs로 매개변수 가져오기 ! AccountApi에 있는..

        BeanPropertyBindingResult bindingResult = null;

        for(Object arg : args) {
            if(arg.getClass() == BeanPropertyBindingResult.class) {
               bindingResult = (BeanPropertyBindingResult) arg;
               break;

            }
        }

        if(bindingResult.hasErrors()) { //에러가 있으면 여기가 실행되면서 errorMap을 만듬
            Map<String, String> errorMap = new HashMap<String, String>();

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for(FieldError fieldError : fieldErrors) {
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());

            }

            throw new CustomValidationException("Validation Error", errorMap);
        }

        Object result = null;
        result = joinPoint.proceed();

        return result;

    }

}
