package com.luopc1218.wordrememberserver.entity;

import lombok.Data;

@Data
public class SysConfig {
    private Integer id;
    private String fieldName;
    private Object value;

    public SysConfig(Integer id, String fieldName, Object value) {
        this.id = id;
        this.fieldName = fieldName;
        this.value = value;
    }
}
