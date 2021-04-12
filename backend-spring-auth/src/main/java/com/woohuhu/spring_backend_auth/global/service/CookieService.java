package com.woohuhu.spring_backend_auth.global.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public interface CookieService {
    Cookie createCookie(String tokenName, String token) throws Exception;

    Cookie getCookie(HttpServletRequest req, String cookieName) throws Exception;
}
