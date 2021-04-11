package com.woohuhu.spring_backend_auth.user.controller;

import com.woohuhu.spring_backend_auth.global.service.JWTService;
import com.woohuhu.spring_backend_auth.user.dto.*;
import com.woohuhu.spring_backend_auth.user.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@EnableAutoConfiguration
@MapperScan(basePackages = "com.woohuhu.spring_backend_auth.user.dao")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    @GetMapping("/v1/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable @Valid String id) throws Exception {
        Object res = userService.getUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping("/v1/user")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) throws Exception {
        Object res = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PostMapping("/v1/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequestDto loginRequestDto) throws Exception {
        UserDto userDto = userService.authenticate(loginRequestDto);
        String accessToken = jwtService.generateAccessToken(userDto);
        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                .accessToken(accessToken)
                .id(userDto.getId())
                .name(userDto.getName())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(loginResponseDto);
    }
}
