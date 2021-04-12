package com.woohuhu.spring_backend_auth.user.controller;

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
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
        String accessToken = jwtService.generateAccessToken(userDto);

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

    @DeleteMapping("/v1/refreshToken/{id}")
    public ResponseEntity deleteRefreshToken(@PathVariable @Valid String id) throws Exception {
        Object result = userService.deleteRefreshToken(id);
        return new ResponseEntity(Response.response(StatusCode.OK, "토큰 삭제 성공", result), HttpStatus.OK);
    }
}
