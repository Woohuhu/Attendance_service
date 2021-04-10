package com.woohuhu.spring_backend_auth.user.service;

import com.woohuhu.spring_backend_auth.user.dao.UserDao;
import com.woohuhu.spring_backend_auth.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.woohuhu.spring_backend_auth.user.dto.UserDto;

import static java.util.Objects.isNull;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    public UserDto getUser(String id) throws Exception{
        UserDto userDto = userDao.getUser(id);

        if(isNull(userDto)){
            throw new UserNotFoundException();
        }
        return userDto;
    }
}
