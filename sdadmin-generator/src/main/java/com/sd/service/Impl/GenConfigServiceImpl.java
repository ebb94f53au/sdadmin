package com.sd.service.Impl;

import com.sd.domain.GenConfig;
import com.sd.mapper.GenConfigMapper;
import com.sd.service.GenConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author siyang
 * @create 2020-01-13 16:41
 *
 */
@Service
public class GenConfigServiceImpl implements GenConfigService {

    @Autowired
    private  GenConfigMapper genConfigMapper;
    /**
     * 查询表配置
     * @param tableName 表名
     * @return 表配置
     */
    @Override
    public GenConfig find(String tableName) {
        GenConfig genConfig = genConfigMapper.findByTableName(tableName);
        //如果数据库中没有配置，则默认不覆盖且模块为system
        if(genConfig == null){
            return new GenConfig(tableName);
        }
        return genConfig;
    }
}
