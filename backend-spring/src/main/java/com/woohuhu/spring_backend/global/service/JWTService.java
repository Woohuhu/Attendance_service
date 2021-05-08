package com.woohuhu.spring_backend.global.service;

import org.springframework.stereotype.Service;

@Service
public interface JWTService {
    void verifyAccessToken(String accessToken) throws Exception;

    void verifyRefreshToken(String refreshToken) throws Exception;
}
