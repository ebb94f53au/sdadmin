package com.sd.modules.system.service.converter;

import com.sd.base.BaseConverter;
import com.sd.modules.system.domain.User;
import com.sd.modules.system.service.dto.DeptSmallDto;
import com.sd.modules.system.service.dto.JobSmallDto;
import com.sd.modules.system.service.dto.RoleSmallDto;
import com.sd.modules.system.service.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

/**
 * @author Zheng Jie
 * @date 2018-11-23
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserConverter extends BaseConverter<UserDto, User> {
    /**
     * 转换
     * @param user 原始数据
     * @return
     */
    @Mappings({
            @Mapping(source = "user.id",target = "id"),
            @Mapping(source = "user.username",target = "username"),
            @Mapping(source = "user.nickName",target = "nickName"),
            @Mapping(source = "user.sex",target = "sex"),
            @Mapping(source = "user.email",target = "email"),
            @Mapping(source = "user.phone",target = "phone"),
            @Mapping(source = "user.enabled",target = "enabled"),
            @Mapping(source = "user.password",target = "password"),
            @Mapping(source = "user.lastPasswordResetTime",target = "lastPasswordResetTime"),
//            @Mapping(source = "user.deptId",target = "deptId"),
            @Mapping(source = "user.createTime",target = "createTime"),
            @Mapping(source = "roles",target = "roles"),
            @Mapping(source = "job",target = "job"),
            @Mapping(source = "dept",target = "dept"),
            @Mapping(source = "avatarName",target = "avatar"),
    })
    UserDto toDto(User user, String avatarName, Set<RoleSmallDto> roles, JobSmallDto job, DeptSmallDto dept);


}
