package com.luopc1218.wordrememberserver.util.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luopc1218.wordrememberserver.entity.ApiResponse;
import com.luopc1218.wordrememberserver.util.annotation.JsonWebTokenRequire;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

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
        ApiResponse fail = ApiResponse.fail("need sign in!");
        fail.setCode(200);
        try {
            //  获取输出流
            ServletOutputStream outputStream = response.getOutputStream();
            //  序列化工具
            ObjectMapper objectMapper = new ObjectMapper();
            //  将 ApiResponse 序列化为 字节
            byte[] bytes = objectMapper.writeValueAsBytes(fail);
            //  写入输出流
            outputStream.write(bytes);
            //  刷新
            response.flushBuffer();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
