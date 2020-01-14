package com.sd.service;

import com.sd.domain.GenConfig;

/**
 * @author siyang
 * @create 2020-01-13 16:41
 */
public interface GenConfigService {

    /**
     * 查询表配置
     * @param tableName 表名
     * @return 表配置
     */
    GenConfig find(String tableName);


}
