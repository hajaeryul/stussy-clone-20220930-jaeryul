package com.stussy.stussyclone20220930jaeryul.api.advice;

import com.stussy.stussyclone20220930jaeryul.dto.CMRespDto;
import com.stussy.stussyclone20220930jaeryul.exception.CustomValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class) //예외가 발생하면 ExceptionHandler가 낚아채서 클래스를만듬
    public ResponseEntity<?> validationErrorException(CustomValidationException e) {

        return ResponseEntity.badRequest().body(new CMRespDto<>(e.getMessage(), e.getErrorMap())); //body에 날리면 JSON으로 날라간다
    }

}
