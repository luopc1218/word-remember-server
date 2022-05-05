package com.luopc1218.wordrememberserver.repository;

import com.luopc1218.wordrememberserver.entity.SysConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysMapper {
    @Select("select * from sys_config;")
    List<SysConfig> getSysConfig();
}
