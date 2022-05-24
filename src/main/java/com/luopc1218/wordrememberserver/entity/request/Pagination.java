package com.luopc1218.wordrememberserver.entity.request;

import lombok.Data;

@Data
public class Pagination {
    private Integer page;
    private Integer pageSize;
    private Integer limit = 0;

    public Pagination(Integer page, Integer pageSize) {
        this.page = page;
        this.pageSize = pageSize;
        this.limit = (page - 1) * this.pageSize;
    }

    public Pagination(String page, String pageSize) {
        this.page = Integer.parseInt(page);
        this.pageSize = Integer.parseInt(pageSize);
        this.limit = (this.page - 1) * this.pageSize;
    }
}
