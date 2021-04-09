package com.woohuhu.spring_backend_auth.user.controller;

import com.woohuhu.spring_backend_auth.user.dto.UserDto;
import com.woohuhu.spring_backend_auth.user.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@EnableAutoConfiguration
@MapperScan(basePackages = "com.woohuhu.spring_backend_auth.user.dao")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/v1/user")
    public UserDto getUser(@PathVariable @Valid String id) throws Exception {
        return userService.getUser(id);
    }
}
