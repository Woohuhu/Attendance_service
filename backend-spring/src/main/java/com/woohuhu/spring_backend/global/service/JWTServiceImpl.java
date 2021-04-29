package com.woohuhu.spring_backend.global.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTServiceImpl implements JWTService {

    @Value("${AccessTokenSecretKey}")
    private String AccessTokenSecretKey;

    @Value("${RefreshTokenSecretKey}")
    private String RefreshTokenSecretKey;

    @Value("${AccessTokenExpires}")
    private Long AccessTokenExpires;

    @Value("${RefreshTokenExpires}")
    private Long RefreshTokenExpires;

    @Override
    public void verifyAccessToken(String accessToken) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(AccessTokenSecretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .acceptExpiresAt(AccessTokenExpires)
                    .build();
            verifier.verify(accessToken);
        } catch (JWTVerificationException exception) {
            throw exception;
        }
    }

    @Override
    public void verifyRefreshToken(String refreshToken) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(RefreshTokenSecretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .acceptExpiresAt(RefreshTokenExpires)
                    .build();
            verifier.verify(refreshToken);
        } catch (JWTVerificationException exception) {
            throw exception;
        }
    }
}
