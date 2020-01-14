package com.sd.modules.security.service;

import com.sd.modules.security.security.vo.JwtUser;
import com.sd.modules.system.domain.User;
import com.sd.modules.system.mapper.DeptMapper;
import com.sd.modules.system.mapper.JobMapper;
import com.sd.modules.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * @author siyang
 * @create 2020-01-13 16:00
 */
@Service
public class JwtUserService {
    @Autowired
    DeptMapper deptMapper;
    @Autowired
    JobMapper jobMapper;
    @Autowired
    RoleService roleService;

    /**
     * 创建jwtUser
     * @param user
     * @return
     */
    public JwtUser createJwtUser(User user){
        ;
        return new JwtUser(user.getId(),
                user.getUsername(),
                user.getNickName(),
                user.getSex(),
                user.getAvatarId(),
                user.getEmail(),
                user.getPhone(),
                deptMapper.selectByPrimaryKey(user.getDeptId()).getName(),
                jobMapper.selectByPrimaryKey(user.getJobId()).getName(),
                roleService.getPermissionAndRolesByUser(user),
                user.getEnabled()==0 ? false :true,
                user.getCreateTime().getTime()
                );
    }

}
