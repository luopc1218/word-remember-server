package com.luopc1218.wordrememberserver.util.interceptor;

import com.luopc1218.wordrememberserver.util.annotation.JsonWebTokenRequire;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class RequestInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 反射获取方法上的LoginRequred注解
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        JsonWebTokenRequire jsonWebTokenRequire = handlerMethod.getMethod().getAnnotation(JsonWebTokenRequire.class);
        if (jsonWebTokenRequire == null) {
            return true;
        }
        System.out.println("need sign in!");
        return false;
    }
}
