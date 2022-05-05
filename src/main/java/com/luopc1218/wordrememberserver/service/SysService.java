package com.luopc1218.wordrememberserver.service;

import com.luopc1218.wordrememberserver.entity.SysConfig;
import com.luopc1218.wordrememberserver.repository.SysMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysService {

    @Autowired
    private SysMapper sysMapper;

    public Map<String, Object> getSysConfig() {
        List<SysConfig> result = sysMapper.getSysConfig();
        Map<String, Object> sysConfigMap = new HashMap<>();
        for (SysConfig sysConfig : result) {
            sysConfigMap.put(sysConfig.getFieldName(), sysConfig.getValue());
        }
        return sysConfigMap;
    }
}
