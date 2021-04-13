package com.woohuhu.spring_backend_auth.user.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.woohuhu.spring_backend_auth.global.dto.Response;
import com.woohuhu.spring_backend_auth.global.dto.StatusCode;
import com.woohuhu.spring_backend_auth.global.service.CookieService;
import com.woohuhu.spring_backend_auth.global.service.JWTService;
import com.woohuhu.spring_backend_auth.user.dto.LoginRequestDto;
import com.woohuhu.spring_backend_auth.user.dto.LoginResponseDto;
import com.woohuhu.spring_backend_auth.user.dto.UserDto;
import com.woohuhu.spring_backend_auth.user.dto.UserInfoDto;
import com.woohuhu.spring_backend_auth.user.exception.UserExsitedException;
import com.woohuhu.spring_backend_auth.user.exception.UserUnauthorizedException;
import com.woohuhu.spring_backend_auth.user.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private CookieService cookieService;

    @GetMapping("/v1/user/{id}")
    public ResponseEntity getUser(@PathVariable @Valid String id) throws Exception {
        try {
            Object result = userService.getUser(id);
            return new ResponseEntity(Response.response(StatusCode.OK, "사용자 조회 성공", result), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity(Response.response(StatusCode.NOT_FOUND, "사용자 조회 실패"), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/v1/user")
    public ResponseEntity createUser(@RequestBody UserDto userDto) throws Exception {
        try {
            userService.createUser(userDto);
            return new ResponseEntity(Response.response(StatusCode.OK, "회원가입 성공"), HttpStatus.CREATED);
        } catch (UserExsitedException e) {
            logger.error(e.getMessage());
            return new ResponseEntity(Response.response(StatusCode.CONFLICT, e.getMessage()), HttpStatus.CONFLICT);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity(Response.response(StatusCode.NOT_FOUND, "회원가입 실패"), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/v1/login")
    public ResponseEntity authenticate(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) throws Exception {
        try {
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
        } catch (UserUnauthorizedException e) {
            logger.error(e.getMessage());
            return new ResponseEntity(Response.response(StatusCode.FORBIDDEN, e.getMessage()), HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity(Response.response(StatusCode.NOT_FOUND, "로그인 실패"), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/v1/refreshtoken/{id}")
    public ResponseEntity deleteRefreshToken(@PathVariable @Valid String id) throws Exception {
        try {
            Object result = userService.deleteRefreshToken(id);
            return new ResponseEntity(Response.response(StatusCode.OK, "토큰 삭제 성공", result), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity(Response.response(StatusCode.NOT_FOUND, "토큰 삭제 실패"), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/v1/refreshtoken")
    public ResponseEntity createRefreshToken(@RequestBody Map<String, Object> body, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Cookie cookie = cookieService.getCookie(request, "refreshtoken");
            UserInfoDto userInfoDto = userService.getUser(body.get("id").toString());

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
            logger.error(e.getMessage());
            return new ResponseEntity(Response.response(StatusCode.UNAUTHORIZED, "토큰 만료"), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity(Response.response(StatusCode.FORBIDDEN, "토큰 재발급 실패"), HttpStatus.FORBIDDEN);
        }
    }
}
