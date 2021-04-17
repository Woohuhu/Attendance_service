package com.woohuhu.spring_backend.global.service;

public interface JWTService {
    void verifyAccessToken(String accessToken) throws Exception;

    void verifyRefreshToken(String refreshToken) throws Exception;
}
