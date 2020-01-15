package com.sd.modules.security.service;

import com.sd.modules.security.security.vo.JwtUser;
import com.sd.modules.system.domain.User;
import com.sd.modules.system.mapper.DeptMapper;
import com.sd.modules.system.mapper.JobMapper;
import com.sd.modules.system.service.RoleService;
import com.sd.modules.system.service.dto.RoleSmallDto;
import com.sd.modules.system.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleStatus;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * @author siyang
 * @create 2020-01-13 16:00
 */
@Service
public class JwtUserService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private JobMapper jobMapper;
    @Autowired
    private RoleService roleService;

    /**
     * 创建jwtUser
     * @param user
     * @return
     */
    public JwtUser createJwtUser(UserDto user){
        //取出角色id集合
        Set<Long> roleIds = new HashSet<>();
        Set<RoleSmallDto> roleSmallDtos = user.getRoles();
        roleSmallDtos.forEach(roleSmallDto->{
            roleIds.add(roleSmallDto.getId());
        });

        return new JwtUser(user.getId(),
                user.getUsername(),
                user.getNickName(),
                user.getSex(),
                user.getAvatar(),
                user.getEmail(),
                user.getPhone(),
                user.getDept().getName(),
                user.getJob().getName(),
                roleService.getPermissionAndRolesByRoleIds(roleIds),
                user.getEnabled()==0 ? false :true,
                user.getCreateTime().getTime()
                );
    }

}
