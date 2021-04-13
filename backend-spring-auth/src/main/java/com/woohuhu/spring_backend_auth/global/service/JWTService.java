package com.woohuhu.spring_backend_auth.global.service;

import com.woohuhu.spring_backend_auth.user.dto.*;

public interface JWTService {
    String generateAccessToken(UserInfoDto userInfoDto) throws Exception;

    String generateRefreshToken(String id) throws Exception;

    void verifyAccessToken(String accessToken) throws Exception;

    void verifyRefreshToken(String refreshToken) throws Exception;
}
