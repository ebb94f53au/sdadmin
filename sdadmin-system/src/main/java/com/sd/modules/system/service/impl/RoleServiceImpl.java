package com.sd.modules.system.service.impl;

import com.sd.modules.system.domain.Role;
import com.sd.modules.system.domain.User;
import com.sd.modules.system.mapper.RoleMapper;
import com.sd.modules.system.mapper.UserMapper;
import com.sd.modules.system.service.RoleService;
import com.sd.modules.system.service.converter.RoleSmallConverter;
import com.sd.modules.system.service.dto.RoleSmallDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleSmallConverter roleSmallConverter;

    @Override
    public Set<String> getPermissionAndRolesByUser(User user) {
        //用户总权限集合
        Set<String> allPermissionAndRoles =new HashSet<>();
        //通过用户id得到他所有的角色

        Set<Long> roleIds = userMapper.getRoleIdsByUsername(user.getUsername());

        return getPermissionAndRolesByRoleIds(roleIds);
    }

    @Override
    public Set<String> getPermissionAndRolesByRoleIds( Set<Long> roleIds) {
        //用户总权限集合
        Set<String> result =new HashSet<>();
        //遍历所有角色获得用户所有权限
        roleIds.forEach(roleId ->{

            Role role = roleMapper.selectByPrimaryKey(roleId);
            Set<String> permissions = roleMapper.getPermissionsByRoleId(role.getId());
            //将权限加入集合
            result.addAll(permissions);
            //将角色加入集合
            result.add(role.getPermission());

        });
        return result;
    }

    @Override
    public List<RoleSmallDto> findRolesByUsername(String username) {
        List<RoleSmallDto> roleSmallDtos =new ArrayList<>();
        Set<Long> roleIds = userMapper.getRoleIdsByUsername(username);

        roleIds.forEach(roleId->{
            Role role = roleMapper.selectByPrimaryKey(roleId);
            RoleSmallDto roleSmallDto = roleSmallConverter.toDto(role);
            roleSmallDtos.add(roleSmallDto);
        });
        return roleSmallDtos;
    }
}
