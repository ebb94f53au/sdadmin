package com.sd.modules.system.service.converter;

import com.sd.base.BaseConverter;
import com.sd.modules.system.domain.Role;
import com.sd.modules.system.service.dto.DeptDto;
import com.sd.modules.system.service.dto.MenuDto;
import com.sd.modules.system.service.dto.RoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

/**
 * @author Zheng Jie
 * @date 2018-11-23
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleConverter extends BaseConverter<RoleDto, Role> {

    @Mappings({
            @Mapping(source = "role.id",target = "id"),
            @Mapping(source = "role.name",target = "name"),
            @Mapping(source = "role.dataScope",target = "dataScope"),
            @Mapping(source = "role.level",target = "level"),
            @Mapping(source = "role.remark",target = "remark"),
            @Mapping(source = "role.permission",target = "permission"),
            @Mapping(source = "role.createTime",target = "createTime"),
            @Mapping(source = "menus",target = "menus"),
            @Mapping(source = "depts",target = "depts"),
    })
    RoleDto toDto(Role role, Set<MenuDto> menus,Set<DeptDto> depts);
}
