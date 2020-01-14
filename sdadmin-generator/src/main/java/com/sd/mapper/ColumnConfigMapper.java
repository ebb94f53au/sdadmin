package com.sd.mapper;

import com.sd.domain.ColumnConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author siyang
 * @create 2020-01-13 16:24
 */
@Mapper
public interface ColumnConfigMapper {
    /**
     * 查询表信息
     * @param tableName 表格名
     * @return 表信息
     */
    List<ColumnConfig> findByTableNameOrderByIdAsc(String tableName);

    /**
     * 保存所有数据
     * @param list
     * @return
     */
    void saveAll(List<ColumnConfig> list);

    ColumnConfig findOne(int id);
}
