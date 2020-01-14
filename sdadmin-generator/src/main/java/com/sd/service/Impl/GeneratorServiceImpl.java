package com.sd.service.Impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.sd.domain.ColumnConfig;
import com.sd.domain.GenConfig;
import com.sd.exception.BadRequestException;
import com.sd.mapper.ColumnConfigMapper;
import com.sd.service.GeneratorService;
import com.sd.utils.GenUtil;
import org.apache.xmlbeans.impl.piccolo.xml.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author siyang
 * @create 2020-01-13 16:38
 */
@Service
public class GeneratorServiceImpl implements GeneratorService {
    @Autowired
    private ColumnConfigMapper columnConfigMapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void generator(GenConfig genConfig, List<ColumnConfig> columns) {
        if(genConfig.getId() == null){
            throw new BadRequestException("请先配置生成器");
        }
        try {
            GenUtil.generatorCode(columns, genConfig);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BadRequestException("生成失败，请手动处理已生成的文件");
        }
    }

    /**
     * 通过表名得到表内所有字段的属性，
     * 如果数据columnconfig 中没有 先查询到 然后添加到数据库中
     * @param name 表名
     * @return
     */
    @Override
    public List<ColumnConfig> getColumns(String name) {
        List<ColumnConfig> columnconfigs = columnConfigMapper.findByTableNameOrderByIdAsc(name);
        if(CollectionUtil.isNotEmpty(columnconfigs)){
            return columnconfigs;
        } else {
            columnconfigs = query(name);
            columnConfigMapper.saveAll(columnconfigs);
            return columnconfigs;
        }
    }

    @Override
    public List<ColumnConfig> query(String tableName) {
        String sql = "select column_name, is_nullable, data_type, column_comment, column_key, extra from information_schema.columns " +
                "where table_name = '"+tableName+"' and table_schema = (select database()) order by ordinal_position";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        List<ColumnConfig> columnInfos = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            columnInfos.add(
                    new ColumnConfig(tableName,
                            map.get("COLUMN_NAME").toString(),
                            "NO".equals(map.get("IS_NULLABLE")),
                            map.get("DATA_TYPE").toString(),
                            ObjectUtil.isNotNull( map.get("COLUMN_COMMENT")) ? map.get("COLUMN_COMMENT").toString() : null,
                            ObjectUtil.isNotNull(map.get("COLUMN_KEY")) ? map.get("COLUMN_KEY").toString() : null,
                            ObjectUtil.isNotNull(map.get("EXTRA")) ? map.get("EXTRA").toString() : null)
            );
        }

        return columnInfos;
    }
}
