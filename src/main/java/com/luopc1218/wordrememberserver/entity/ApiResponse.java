package com.luopc1218.wordrememberserver.entity;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private Integer code;
    private String message;
    private Boolean success;
    private T data;

    private ApiResponse(Integer code, String message, boolean success, T data) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.data = data;
    }

    private ApiResponse(ApiResponseStatus apiResponseStatus, T data) {
        this.code = apiResponseStatus.getCode();
        this.message = apiResponseStatus.getMessage();
        this.success = apiResponseStatus.getSuccess();
        this.data = data;
    }

    public static ApiResponse success() {
        return new ApiResponse(ApiResponseStatus.SUCCESS, null);
    }

    public static <E> ApiResponse success(E data) {
        return new ApiResponse<>(ApiResponseStatus.SUCCESS, data);
    }

    public static ApiResponse fail() {
        return new ApiResponse(ApiResponseStatus.FAIL, null);
    }

    public static ApiResponse fail(String message) {
        return new ApiResponse(200, message, false, null);
    }

    public static ApiResponse fail(ApiResponseStatus apiResponseStatus) {
        return new ApiResponse(apiResponseStatus, null);
    }

}
