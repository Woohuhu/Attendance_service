package com.woohuhu.spring_backend_auth.user.service;

import com.woohuhu.spring_backend_auth.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.woohuhu.spring_backend_auth.user.dto.UserDto;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    public UserDto getUser(String id) throws Exception{
        UserDto userDto = userDao.getUser(id);
        return userDto;
    }
}
