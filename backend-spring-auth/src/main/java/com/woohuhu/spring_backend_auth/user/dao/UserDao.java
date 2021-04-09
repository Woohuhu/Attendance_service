package com.woohuhu.spring_backend_auth.user.dao;

import com.woohuhu.spring_backend_auth.user.Dto.UserDto;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    UserDto getUser() throws Exception;
}
