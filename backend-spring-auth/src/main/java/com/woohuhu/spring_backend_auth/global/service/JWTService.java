package com.woohuhu.spring_backend_auth.global.service;

import com.woohuhu.spring_backend_auth.user.dto.UserDto;

public interface JWTService {
    String generateAccessToken(UserDto userDto) throws Exception;

    String generateRefreshToken(String id) throws Exception;

    String verifyAccessToken(String accessToken) throws Exception;

    String verifyRefreshToken(String refreshToken) throws Exception;
}
