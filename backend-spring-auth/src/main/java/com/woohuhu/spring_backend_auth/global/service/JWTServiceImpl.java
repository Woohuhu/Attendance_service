package com.woohuhu.spring_backend_auth.global.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.woohuhu.spring_backend_auth.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    public String generateAccessToken(UserDto userDto) {
        try {
            Date ext = new Date();
            ext.setTime(ext.getTime() + AccessTokenExpires);
            Algorithm algorithm = Algorithm.HMAC256(AccessTokenSecretKey);
            String accessToken = JWT.create()
                    .withClaim("id", userDto.getId())
                    .withClaim("name", userDto.getName())
                    .withExpiresAt(ext)
                    .sign(algorithm);
            return accessToken;
        } catch (JWTCreationException exception) {
            throw exception;
        }
    }

    @Override
    public String generateRefreshToken(String id) {
        try {
            Date ext = new Date();
            ext.setTime(ext.getTime() + RefreshTokenExpires);
            Algorithm algorithm = Algorithm.HMAC256(RefreshTokenSecretKey);
            String refreshToken = JWT.create()
                    .withClaim("id", id)
                    .withExpiresAt(ext)
                    .sign(algorithm);
            return refreshToken;
        } catch (JWTCreationException exception) {
            throw exception;
        }
    }

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
