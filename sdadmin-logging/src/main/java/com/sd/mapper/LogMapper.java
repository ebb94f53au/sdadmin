package com.sd.mapper;

import com.sd.domain.Log;
import com.sd.domain.LogExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LogMapper {
    long countByExample(LogExample example);

    int deleteByExample(LogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Log record);

    int insertSelective(Log record);

    List<Log> selectByExampleWithBLOBs(LogExample example);

    List<Log> selectByExample(LogExample example);

    Log selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Log record, @Param("example") LogExample example);

    int updateByExampleWithBLOBs(@Param("record") Log record, @Param("example") LogExample example);

    int updateByExample(@Param("record") Log record, @Param("example") LogExample example);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKeyWithBLOBs(Log record);

    int updateByPrimaryKey(Log record);

    /**
     * 获取一段时间的ip访问数量
     * @param d1 2020-01-02
     * @param d2
     * @return
     */
    Long findIpCountByTime(String d1,String d2);
}