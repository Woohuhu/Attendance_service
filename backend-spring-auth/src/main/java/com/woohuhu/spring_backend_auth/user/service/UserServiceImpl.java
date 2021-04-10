package com.woohuhu.spring_backend_auth.user.service;

import com.woohuhu.spring_backend_auth.user.dao.UserDao;
import com.woohuhu.spring_backend_auth.user.exception.UserExsitedException;
import com.woohuhu.spring_backend_auth.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.woohuhu.spring_backend_auth.user.dto.UserDto;

import static java.util.Objects.isNull;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto getUser(String id) throws Exception{
        UserDto userDto = userDao.getUser(id);

        if(isNull(userDto)){
            throw new UserNotFoundException();
        }
        return userDto;
    }

    @Override
    public int createUser(UserDto userDto) throws Exception{
        UserDto exsited = userDao.getUser(userDto.getId());
        if(!isNull(exsited)){
            throw new UserExsitedException();
        }
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        UserDto newUser = UserDto.builder()
                .id(userDto.getId())
                .password(encodedPassword)
                .name(userDto.getName())
                .build();
        return userDao.createUser(newUser);
    }
}
