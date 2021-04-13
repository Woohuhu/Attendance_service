package com.woohuhu.spring_backend_auth.user.service;

import com.woohuhu.spring_backend_auth.user.dao.UserDao;
import com.woohuhu.spring_backend_auth.user.dto.LoginRequestDto;
import com.woohuhu.spring_backend_auth.user.dto.RefreshTokenDto;
import com.woohuhu.spring_backend_auth.user.dto.UserDto;
import com.woohuhu.spring_backend_auth.user.dto.UserInfoDto;
import com.woohuhu.spring_backend_auth.user.exception.UserExsitedException;
import com.woohuhu.spring_backend_auth.user.exception.UserNotFoundException;
import com.woohuhu.spring_backend_auth.user.exception.UserUnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserInfoDto getUser(String id) throws Exception {
        UserDto userDto = userDao.getUser(id);

        if (isNull(userDto)) {
            throw new UserNotFoundException();
        }

        UserInfoDto userInfoDto = UserInfoDto.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .build();
        return userInfoDto;
    }

    @Override
    public int createUser(UserDto userDto) throws Exception {
        UserDto exsited = userDao.getUser(userDto.getId());
        if (!isNull(exsited)) {
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

    @Override
    public UserDto authenticate(LoginRequestDto loginRequestDto) throws Exception {
        UserDto userDto = userDao.getUser(loginRequestDto.getId());

        if (isNull(userDto) || !passwordEncoder.matches(loginRequestDto.getPassword(), userDto.getPassword())) {
            throw new UserUnauthorizedException();
        }

        return userDto;
    }

    @Override
    public int createRefreshToken(String id, String refreshToken) throws Exception {
        RefreshTokenDto newRefreshToken = RefreshTokenDto.builder()
                .id(id)
                .token(refreshToken)
                .build();
        return userDao.createRefreshToken(newRefreshToken);
    }

    @Override
    public int deleteRefreshToken(String id) throws Exception {
        int result = userDao.deleteRefreshToken(id);

        return result;
    }
}
