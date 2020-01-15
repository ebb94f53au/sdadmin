package com.sd.modules.system.service;

import com.sd.modules.system.domain.User;
import com.sd.modules.system.service.dto.RoleSmallDto;

import java.util.List;
import java.util.Set;

/**
 * @author siyang
 * @create 2020-01-14 15:05
 */
public interface RoleService {

    /**
     * 通过user获得用户所有权限及角色
     * @return
     */
    public Set<String> getPermissionAndRolesByUser(User user);
    /**
     * 通过角色ids获得用户所有权限及角色
     * @return
     */
    public Set<String> getPermissionAndRolesByRoleIds(Set<Long> roleIds);

    /**
     * 根据用户id 获得所有角色
     * @return
     */
    public List<RoleSmallDto> findRolesByUsername(String username);
}
