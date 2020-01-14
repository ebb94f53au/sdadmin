package com.sd.service;

import com.sd.domain.ColumnConfig;
import com.sd.domain.GenConfig;

import java.util.List;

/**
 * @author siyang
 * @create 2020-01-13 16:38
 */
public interface GeneratorService {

    /**
     * 代码生成
     * @param genConfig 配置信息
     * @param columns 字段信息
     */
    void generator(GenConfig genConfig, List<ColumnConfig> columns);

    /**
     * 得到数据表的元数据
     * @param name 表名
     * @return /
     */
    List<ColumnConfig> getColumns(String name);

    /**
     * 查询数据库的表字段数据数据
     * @param table /
     * @return /
     */
    List<ColumnConfig> query(String table);
}
