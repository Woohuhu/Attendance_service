package com.woohuhu.spring_backend_auth.user.exception;

public class UserUnauthorizedException extends RuntimeException {

    private static final String USER_Unauthorized_EXCEPTION_MESSAGE = "아이디와 비밀번호를 확인해주세요.";

    public UserUnauthorizedException() {
        super(USER_Unauthorized_EXCEPTION_MESSAGE);
    }
}
