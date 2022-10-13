package com.stussy.stussyclone20220930jaeryul.repository;

import com.stussy.stussyclone20220930jaeryul.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper //이거 있어야 ioc 등록, xml이랑 연결도된당
public interface AccountRepository {

    public User findUserByEmail(String email) throws Exception;
    public int saveUser(User user) throws Exception;

}
