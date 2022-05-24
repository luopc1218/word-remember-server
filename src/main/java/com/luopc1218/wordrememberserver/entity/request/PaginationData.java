package com.luopc1218.wordrememberserver.entity.request;

import lombok.Data;

import java.util.List;

@Data
public class PaginationData<T> {
    private List<T> list;
    private Integer totalCount;

    public PaginationData(List<T> list, Integer totalCount) {
        this.list = list;
        this.totalCount = totalCount;
    }
}
