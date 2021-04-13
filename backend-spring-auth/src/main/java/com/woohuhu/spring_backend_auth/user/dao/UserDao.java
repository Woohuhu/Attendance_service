package com.woohuhu.spring_backend_auth.user.dao;

import com.woohuhu.spring_backend_auth.user.dto.*;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    UserDto getUser(String id) throws Exception;

    int createUser(UserDto userDto) throws Exception;

    int createRefreshToken(RefreshTokenDto refreshTokenDto) throws Exception;

    RefreshTokenDto getRefreshToken(String id) throws Exception;

    int deleteRefreshToken(String id) throws Exception;
}
