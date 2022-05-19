package com.luopc1218.wordrememberserver.entity.ApiResponse;

import lombok.Data;

import java.util.List;

@Data
public class ApiResponseList {
    private List<Object> data;
    private Integer totalCount;


    public ApiResponseList(List<Object> data, Integer totalCount) {
        this.data = data;
        this.totalCount = totalCount;
    }
}
