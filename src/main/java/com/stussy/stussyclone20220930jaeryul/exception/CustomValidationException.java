package com.stussy.stussyclone20220930jaeryul.exception;

import lombok.Getter;

import java.util.Map;

@Getter//에러맵에있는 데이터 뽑아오기위해서
public class CustomValidationException  extends RuntimeException {

    private Map<String, String> errorMap;

    public CustomValidationException(String message, Map<String, String> errorMap) {
        super(message); //super는 RuntimeException 생성자
        this.errorMap = errorMap;
    }

}
