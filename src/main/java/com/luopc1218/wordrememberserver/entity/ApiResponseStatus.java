package com.luopc1218.wordrememberserver.entity;

public enum ApiResponseStatus {
    SUCCESS(200, "", true),
    FAIL(-1, "", false),
    DEVELOPING(-1, "接口开发中", false);

    private Integer code;
    private String message;
    private final Boolean success;

    ApiResponseStatus(Integer code, String message, Boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getSuccess() {
        return success;
    }

}
