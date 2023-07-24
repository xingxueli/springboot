package com.company.project.core.interceptor;

import com.company.project.core.util.JwtUtil;
import com.company.project.core.util.UserContext;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessToken = request.getHeader("accessToken");
        if (StringUtils.isNotBlank(accessToken)){
            UserContext.UserSession user = JwtUtil.getUser(accessToken);
            UserContext.setUser(user);
        }
        System.out.println("日志拦截器，方法前拦截...");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("日志拦截器，方法后拦截...");
        UserContext.removeUser();
    }
}
