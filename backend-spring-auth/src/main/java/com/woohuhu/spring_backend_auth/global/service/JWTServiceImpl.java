package com.woohuhu.spring_backend_auth.global.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.woohuhu.spring_backend_auth.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTServiceImpl implements JWTService {

    @Value("${AccessTokenSecretKey}")
    private String AccessTokenSecretKey;

    @Value("${RefreshTokenSecretKey")
    private String RefreshTokenSecretKey;

    @Override
    public String generateAccessToken(UserDto userDto) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(AccessTokenSecretKey);
            String accessToken = JWT.create()
                    .withClaim("id", userDto.getId())
                    .withClaim("name", userDto.getName())
                    .sign(algorithm);
            return accessToken;
        } catch (JWTCreationException exception) {
            throw exception;
        }
    }

    @Override
    public String generateRefreshToken(String id) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(RefreshTokenSecretKey);
            String refreshToken = JWT.create()
                    .withClaim("id", id)
                    .sign(algorithm);
            return refreshToken;
        } catch (JWTCreationException exception) {
            throw exception;
        }
    }
}
