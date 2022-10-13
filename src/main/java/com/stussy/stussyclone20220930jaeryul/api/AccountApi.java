package com.stussy.stussyclone20220930jaeryul.api;

import com.stussy.stussyclone20220930jaeryul.aop.annotation.LogAspect;
import com.stussy.stussyclone20220930jaeryul.dto.CMRespDto;
import com.stussy.stussyclone20220930jaeryul.dto.RegisterReqDto;
import com.stussy.stussyclone20220930jaeryul.dto.validation.ValidationSequence;
import com.stussy.stussyclone20220930jaeryul.exception.CustomValidationException;
import com.stussy.stussyclone20220930jaeryul.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/account")
@RestController
@RequiredArgsConstructor
public class AccountApi {

    private final AccountService accountService;//이녀석은 ioc에서 @requiredArgsConstructor로 들고왔다.

    @LogAspect
    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated(ValidationSequence.class) @RequestBody RegisterReqDto registerReqDto,
                                      BindingResult bindingResult) throws Exception {

        accountService.duplicateEmail(registerReqDto);//여기서 exception이 안터지면 밑으로 내려감~
        accountService.register(registerReqDto);

        return ResponseEntity.created(URI.create("/account/login")).body(new CMRespDto<>("회원가입 성공", registerReqDto.getEmail()));
    }
}