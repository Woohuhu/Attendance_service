package com.woohuhu.spring_backend_auth.user.service;

import com.woohuhu.spring_backend_auth.user.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDto getUser(String id) throws Exception;
}
