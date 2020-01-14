package com.sd.mapper;

import com.sd.domain.GenConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author siyang
 * @create 2020-01-13 16:20
 */
@Mapper
public interface GenConfigMapper {
    /**
     * 查询表配置
     * @param tableName 表名
     * @return /
     */
    GenConfig findByTableName(String tableName);




}
