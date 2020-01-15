package com.sd.modules.system.service.converter;

import com.sd.base.BaseConverter;
import com.sd.modules.system.domain.Dept;
import com.sd.modules.system.service.dto.DeptDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
* @author Zheng Jie
* @date 2019-03-25
*/
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeptConverter extends BaseConverter<DeptDto, Dept> {

    /**
     * 子节点可以为null
     * @param dept
     * @param children
     * @return
     */
    @Mappings({
            @Mapping( source = "dept.id" ,target = "id"),
            @Mapping( source = "dept.name" ,target = "name"),
            @Mapping( source = "dept.enabled" ,target = "enabled"),
            @Mapping( source = "dept.pid" ,target = "pid"),
            @Mapping( source = "dept.createTime" ,target = "createTime"),
            @Mapping( source = "children" ,target = "children"),
    })
    DeptDto toDto(Dept dept, List<DeptDto> children);
}