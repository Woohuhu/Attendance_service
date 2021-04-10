package com.woohuhu.spring_backend_auth.user.exception;

public class UserExsitedException extends RuntimeException {

    private static final String USER_EXSITED_EXCEPTION_MESSAGE = "해당 아이디는 사용 중 입니다.";

    public UserExsitedException() {
        super(USER_EXSITED_EXCEPTION_MESSAGE);
    }
}
