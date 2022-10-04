package com.stussy.stussyclone20220930jaeryul.dto;


import lombok.Data;

@Data /*request는 getter setter만 있으면 되니께*/
public class RegisterReqDto {

    private String lastName;
    private String firstName;
    private String email;
    private String password;
}
