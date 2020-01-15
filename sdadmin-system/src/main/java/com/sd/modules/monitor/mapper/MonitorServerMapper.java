package com.sd.modules.monitor.mapper;

import com.sd.modules.monitor.domain.MonitorServer;
import com.sd.modules.monitor.domain.MonitorServerExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MonitorServerMapper {
    long countByExample(MonitorServerExample example);

    int deleteByExample(MonitorServerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MonitorServer record);

    int insertSelective(MonitorServer record);

    List<MonitorServer> selectByExample(MonitorServerExample example);

    MonitorServer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MonitorServer record, @Param("example") MonitorServerExample example);

    int updateByExample(@Param("record") MonitorServer record, @Param("example") MonitorServerExample example);

    int updateByPrimaryKeySelective(MonitorServer record);

    int updateByPrimaryKey(MonitorServer record);
}