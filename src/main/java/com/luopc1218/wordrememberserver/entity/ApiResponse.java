package com.luopc1218.wordrememberserver.entity;

import lombok.Data;

@Data
public class ApiResponse {
    private Integer code;
    private String message;
    private Boolean success;
    private Object data;


    private ApiResponse(Integer code, String message, boolean success, Object data) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.data = data;
    }

    private ApiResponse(ApiResponseStatus apiResponseStatus, Object data) {
        this.code = apiResponseStatus.getCode();
        this.message = apiResponseStatus.getMessage();
        this.success = apiResponseStatus.getSuccess();
        this.data = data;
    }

    public static ApiResponse success() {
        return new ApiResponse(ApiResponseStatus.SUCCESS, null);
    }

    public static ApiResponse success(Object data) {
        return new ApiResponse(ApiResponseStatus.SUCCESS, data);
    }

    public static ApiResponse success(String message) {
        return new ApiResponse(200, message, true, null);
    }

    public static ApiResponse success(Object data, String message) {
        return new ApiResponse(200, message, true, data);
    }

    public static ApiResponse fail(String message) {
        return new ApiResponse(200, message, false, null);
    }

    public static ApiResponse fail(ApiResponseStatus apiResponseStatus) {
        return new ApiResponse(apiResponseStatus, null);
    }

}
