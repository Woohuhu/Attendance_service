package com.woohuhu.spring_backend_auth.user.service;

import com.woohuhu.spring_backend_auth.user.dto.*;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserInfoDto getUser(String id) throws Exception;

    int createUser(UserDto userDto) throws Exception;

    UserDto authenticate(LoginRequestDto loginRequestDto) throws Exception;

    int createRefreshToken(String id, String refreshToken) throws Exception;

    int deleteRefreshToken(String id) throws Exception;
}
