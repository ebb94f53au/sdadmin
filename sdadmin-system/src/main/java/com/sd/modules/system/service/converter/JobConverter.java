package com.sd.modules.system.service.converter;

import com.sd.base.BaseConverter;
import com.sd.modules.system.domain.Job;
import com.sd.modules.system.service.dto.DeptDto;
import com.sd.modules.system.service.dto.JobDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

/**
* @author Zheng Jie
* @date 2019-03-29
*/
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobConverter extends BaseConverter<JobDto, Job> {

    /**
     * 转Dto
     * @param job 原始数据
     * @return /
     */
    @Mappings({
    @Mapping(source = "job.id", target = "id"),
    @Mapping(source = "job.sort", target = "sort"),
    @Mapping(source = "job.name", target = "name"),
    @Mapping(source = "job.enabled", target = "enabled"),
    @Mapping(source = "job.createTime", target = "createTime"),
    @Mapping(source = "deptSuperiorName", target = "deptSuperiorName"),
    @Mapping(source = "dept", target = "dept"),
    })
    JobDto toDto(Job job, String deptSuperiorName,DeptDto dept);
}