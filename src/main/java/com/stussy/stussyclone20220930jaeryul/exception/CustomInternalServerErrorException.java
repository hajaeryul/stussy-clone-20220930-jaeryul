package com.stussy.stussyclone20220930jaeryul.exception;

public class CustomInternalServerErrorException extends RuntimeException{
    public CustomInternalServerErrorException(String message) {
        super(message);
    }
}
