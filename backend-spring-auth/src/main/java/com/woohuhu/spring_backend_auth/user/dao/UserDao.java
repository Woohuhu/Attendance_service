package com.woohuhu.spring_backend_auth.user.dao;

import com.woohuhu.spring_backend_auth.user.dto.UserDto;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    UserDto getUser(String id) throws Exception;
    void createUser(UserDto userDto) throws Exception;
}
