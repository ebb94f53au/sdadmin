package com.sd.modules.system.service.impl;

import com.sd.modules.system.domain.Role;
import com.sd.modules.system.domain.User;
import com.sd.modules.system.domain.UsersRoles;
import com.sd.modules.system.domain.UsersRolesExample;
import com.sd.modules.system.mapper.RoleMapper;
import com.sd.modules.system.mapper.UsersRolesMapper;
import com.sd.modules.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author siyang
 * @create 2020-01-14 15:05
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    UsersRolesMapper usersRolesMapper;
    @Autowired
    RoleMapper roleMapper;

    @Override
    public Set<String> getPermissionAndRolesByUser(User user) {
        //用户总权限集合
        Set<String> allPermissionAndRoles =new HashSet<>();
        //通过用户id得到他所有的角色
        UsersRolesExample usersRolesExample = new UsersRolesExample();
        usersRolesExample.createCriteria().andUserIdEqualTo(user.getId());
        List<UsersRoles> usersRoles = usersRolesMapper.selectByExample(usersRolesExample);
        //遍历所有角色获得用户所有权限
        usersRoles.forEach(userRole ->{

            Role role = roleMapper.selectByPrimaryKey(userRole.getRoleId());
            Set<String> permissions = roleMapper.getPermissionsByRoleId(role.getId());
            //将权限加入集合
            allPermissionAndRoles.addAll(permissions);
            //将角色加入集合
            allPermissionAndRoles.add(role.getPermission());

        });


        return allPermissionAndRoles;
    }
}
