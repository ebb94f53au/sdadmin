package com.sd.modules.system.service.converter;

import com.sd.base.BaseConverter;
import com.sd.modules.system.domain.Menu;
import com.sd.modules.system.service.dto.MenuDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * @author Zheng Jie
 * @date 2018-12-17
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuConverter extends BaseConverter<MenuDto, Menu> {
    @Mappings({
            @Mapping(source = "menu.id",target = "id"),
            @Mapping(source = "menu.type",target = "type"),
            @Mapping(source = "menu.permission",target = "permission"),
            @Mapping(source = "menu.name",target = "name"),
            @Mapping(source = "menu.sort",target = "sort"),
            @Mapping(source = "menu.path",target = "path"),
            @Mapping(source = "menu.component",target = "component"),
            @Mapping(source = "menu.pid",target = "pid"),
            @Mapping(source = "menu.iFrame",target = "IFrame"),//方法名存在问题。必须这样写
            @Mapping(source = "menu.cache",target = "cache"),
            @Mapping(source = "menu.hidden",target = "hidden"),
            @Mapping(source = "menu.componentName",target = "componentName"),
            @Mapping(source = "menu.icon",target = "icon"),
            @Mapping(source = "menu.createTime",target = "createTime"),
            @Mapping(source = "children",target = "children")
    })
    MenuDto toDto(Menu menu, List<MenuDto> children);

    @Override
    @Mapping(source = "menu.iFrame",target = "IFrame")
    MenuDto toDto(Menu menu);
}
