package com.woohuhu.spring_backend.global.interceptor;

import com.woohuhu.spring_backend.global.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class JwtInterceptor implements HandlerInterceptor {
    private static final String HEADER_AUTH = "Authorization";

    @Autowired
    private JWTService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String token = (request.getHeader(HEADER_AUTH).substring("Bearer ".length()));
        try {
            jwtService.verifyAccessToken(token);
            return true;
        } catch (Exception exception) {
            response.setStatus(401);
            response.getWriter().write("Expired token!");
            return false;
        }
    }
}
