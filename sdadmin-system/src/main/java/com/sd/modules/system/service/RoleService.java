package com.sd.modules.system.service;

import com.sd.modules.system.domain.User;

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
}
