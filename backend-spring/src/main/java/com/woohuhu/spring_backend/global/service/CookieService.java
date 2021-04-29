package com.woohuhu.spring_backend.global.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public interface CookieService {
    Cookie getCookie(HttpServletRequest req, String cookieName) throws Exception;
}
