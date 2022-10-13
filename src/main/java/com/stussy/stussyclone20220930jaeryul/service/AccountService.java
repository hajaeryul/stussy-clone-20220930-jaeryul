package com.stussy.stussyclone20220930jaeryul.service;

import com.stussy.stussyclone20220930jaeryul.dto.RegisterReqDto;


public interface AccountService {

    public void duplicateEmail(RegisterReqDto registerReqDto) throws Exception;
    public void register(RegisterReqDto registerReqDto) throws Exception;


}
