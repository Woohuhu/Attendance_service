package com.woohuhu.spring_backend_auth.global.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Service
public class CookieServiceImpl implements CookieService{

    @Value("${RefreshTokenExpires}")
    private int RefreshTokenExpires;

    @Override
    public Cookie createCookie(String cookieName, String value) throws Exception{
        Cookie cookie = new Cookie(cookieName,value);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(RefreshTokenExpires);
        cookie.setPath("/");
        return cookie;
    }

    @Override
    public Cookie getCookie(HttpServletRequest req, String cookieName) throws Exception{
        final Cookie[] cookies = req.getCookies();
        if(cookies==null) return null;
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(cookieName))
                return cookie;
        }
        return null;
    }
}
