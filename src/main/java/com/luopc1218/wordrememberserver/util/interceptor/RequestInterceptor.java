package com.luopc1218.wordrememberserver.util.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luopc1218.wordrememberserver.entity.ApiResponse;
import com.luopc1218.wordrememberserver.entity.ApiResponseStatus;
import com.luopc1218.wordrememberserver.util.annotation.JsonWebTokenRequire;
import com.luopc1218.wordrememberserver.util.jwt.Jwt;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.io.IOException;

public class RequestInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if (handler instanceof ResourceHttpRequestHandler) {

        } else {
            // 反射获取方法上的LoginRequred注解
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            JsonWebTokenRequire jsonWebTokenRequire = handlerMethod.getMethod().getAnnotation(JsonWebTokenRequire.class);
//        需要token校验
            if (jsonWebTokenRequire != null) {
                String jwtToken = request.getHeader("Authorization");
                ApiResponse fail;
                if (jwtToken == null) {
//            没有token
                    fail = ApiResponse.fail(ApiResponseStatus.NEED_SIGN_IN);
                } else if (Jwt.checkJwtToken(jwtToken)) {
                    return true;
                } else {
//            token失效
                    fail = ApiResponse.fail(ApiResponseStatus.SIGN_IN_EXPIRED);
                }
                try {
                    //  获取输出流
                    ServletOutputStream outputStream = response.getOutputStream();
                    //  序列化工具
                    ObjectMapper objectMapper = new ObjectMapper();
                    //  将 ApiResponse 序列化为 字节
                    byte[] bytes = objectMapper.writeValueAsBytes(fail);
                    //  写入输出流
                    outputStream.write(bytes);
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json;charset=UTF-8");
                    //  刷新
                    response.flushBuffer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return true;
    }
}
