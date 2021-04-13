package com.woohuhu.spring_backend_auth.user.controller;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.woohuhu.spring_backend_auth.global.dto.*;
import com.woohuhu.spring_backend_auth.global.service.*;
import com.woohuhu.spring_backend_auth.user.dto.*;
import com.woohuhu.spring_backend_auth.user.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@RestController
@EnableAutoConfiguration
@MapperScan(basePackages = "com.woohuhu.spring_backend_auth.user.dao")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private CookieService cookieService;

    @GetMapping("/v1/user/{id}")
    public ResponseEntity getUser(@PathVariable @Valid String id) throws Exception {
        Object result = userService.getUser(id);
        return new ResponseEntity(Response.response(StatusCode.OK, "사용자 조회 성공", result), HttpStatus.OK);
    }

    @PostMapping("/v1/user")
    public ResponseEntity createUser(@RequestBody UserDto userDto) throws Exception {
        userService.createUser(userDto);
        return new ResponseEntity(Response.response(StatusCode.OK, "회원가입 성공"), HttpStatus.CREATED);
    }

    @PostMapping("/v1/login")
    public ResponseEntity authenticate(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) throws Exception {
        UserDto userDto = userService.authenticate(loginRequestDto);
        UserInfoDto userInfoDto = UserInfoDto.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .build();
        String accessToken = jwtService.generateAccessToken(userInfoDto);

        String refreshToken = jwtService.generateRefreshToken(userDto.getId());
        userService.createRefreshToken(userDto.getId(), refreshToken);

        Cookie RefreshToken = cookieService.createCookie("refreshtoken", refreshToken);
        response.addCookie(RefreshToken);

        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                .accessToken(accessToken)
                .id(userDto.getId())
                .name(userDto.getName())
                .build();
        return new ResponseEntity(Response.response(StatusCode.OK, "로그인 성공", loginResponseDto), HttpStatus.OK);
    }

    @DeleteMapping("/v1/refreshtoken/{id}")
    public ResponseEntity deleteRefreshToken(@PathVariable @Valid String id) throws Exception {
        Object result = userService.deleteRefreshToken(id);
        return new ResponseEntity(Response.response(StatusCode.OK, "토큰 삭제 성공", result), HttpStatus.OK);
    }

    @PostMapping("/v1/refreshtoken")
    public ResponseEntity createRefreshToken(@RequestBody Map<String, Object> body, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Cookie cookie = cookieService.getCookie(request, "refreshtoken");
        UserInfoDto userInfoDto = userService.getUser(body.get("id").toString());

        try {
            jwtService.verifyRefreshToken(cookie.getValue());

            String newAccessToken = jwtService.generateAccessToken(userInfoDto);

            String newRefreshToken = jwtService.generateRefreshToken(userInfoDto.getId());
            userService.deleteRefreshToken(userInfoDto.getId());
            userService.createRefreshToken(userInfoDto.getId(), newRefreshToken);

            Cookie RefreshToken = cookieService.createCookie("refreshtoken", newRefreshToken);
            response.addCookie(RefreshToken);

            LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                    .accessToken(newAccessToken)
                    .id(userInfoDto.getId())
                    .name(userInfoDto.getName())
                    .build();

            return new ResponseEntity(Response.response(StatusCode.CREATED, "토큰 재발급 성공", loginResponseDto), HttpStatus.CREATED);
        } catch (JWTVerificationException e) {
            return new ResponseEntity(Response.response(StatusCode.UNAUTHORIZED, "토큰 만료"), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity(Response.response(StatusCode.FORBIDDEN, "토큰 재발급 실패"), HttpStatus.FORBIDDEN);
        }
    }
}
