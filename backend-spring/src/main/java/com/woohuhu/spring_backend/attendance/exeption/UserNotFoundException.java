package com.woohuhu.spring_backend.attendance.exeption;

public class UserNotFoundException extends RuntimeException {

    private static final String USER_NOT_FOUND_EXCEPTION_MESSAGE = "해당하는 유저를 찾을 수 없습니다.";

    public UserNotFoundException() {
        super(USER_NOT_FOUND_EXCEPTION_MESSAGE);
    }
}
