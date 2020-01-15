package com.sd.modules.system.service.impl;

import com.sd.exception.BadRequestException;
import com.sd.modules.system.domain.Role;
import com.sd.modules.system.domain.User;
import com.sd.modules.system.domain.UserAvatar;
import com.sd.modules.system.mapper.*;
import com.sd.modules.system.service.UserService;
import com.sd.modules.system.service.converter.DeptSmallConverter;
import com.sd.modules.system.service.converter.JobSmallConverter;
import com.sd.modules.system.service.converter.RoleSmallConverter;
import com.sd.modules.system.service.converter.UserConverter;
import com.sd.modules.system.service.dto.DeptSmallDto;
import com.sd.modules.system.service.dto.JobSmallDto;
import com.sd.modules.system.service.dto.RoleSmallDto;
import com.sd.modules.system.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author siyang
 * @create 2020-01-12 20:01
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserAvatarMapper userAvatarMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private JobMapper jobMapper;
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private RoleSmallConverter roleSmallConverter;
    @Autowired
    private JobSmallConverter jobSmallConverter;
    @Autowired
    private DeptSmallConverter deptSmallConverter;

    public UserDto getUserByUsername(String username) throws BadRequestException {
        UserDto userDto=null;
        User user = userMapper.getUserByUsername(username);
        if(user==null){
            throw new BadRequestException("用户不存在");

        }else{
            if(user.getEnabled()==0){
                throw new BadRequestException("用户未激活");
            }
            //用户头像
            UserAvatar userAvatar = userAvatarMapper.selectByPrimaryKey(user.getAvatarId());

            //角色
            Set<RoleSmallDto> roles= new HashSet<>();
            Set<Long> roleIds = userMapper.getRoleIdsByUsername(user.getUsername());
            roleIds.forEach(roleId->{
                Role role = roleMapper.selectByPrimaryKey(roleId);
                //加入集合
                roles.add(roleSmallConverter.toDto(role));
            });

            //工作
            JobSmallDto job = jobSmallConverter.toDto(jobMapper.selectByPrimaryKey(user.getJobId()));

            //部门
            DeptSmallDto dept = deptSmallConverter.toDto(deptMapper.selectByPrimaryKey(user.getDeptId()));

            //构建userDto对象
            userDto = userConverter.toDto(user,
                   userAvatar==null? null:userAvatar.getRealName(),
                    roles,
                    job,
                    dept);

            return userDto;
        }
    }

    public UserDto toDto(User user, String avatarName, Set<RoleSmallDto> roles, JobSmallDto job, DeptSmallDto dept) {
        if (user == null && avatarName == null && roles == null && job == null && dept == null) {
            return null;
        } else {
            UserDto userDto = new UserDto();
            if (user != null) {
                userDto.setNickName(user.getNickName());
                userDto.setSex(user.getSex());
                userDto.setEnabled(user.getEnabled());
                userDto.setPassword(user.getPassword());
                userDto.setPhone(user.getPhone());
                userDto.setCreateTime(user.getCreateTime());
                userDto.setId(user.getId());
                userDto.setEmail(user.getEmail());
                userDto.setUsername(user.getUsername());
                userDto.setLastPasswordResetTime(user.getLastPasswordResetTime());
            }

            if (avatarName != null) {
                userDto.setAvatar(avatarName);
            }

            if (roles != null) {
                if (roles != null) {
                    userDto.setRoles(new HashSet(roles));
                } else {
                    userDto.setRoles((Set)null);
                }
            }

            if (job != null) {
                userDto.setJob(job);
            }

            if (dept != null) {
                userDto.setDept(dept);
            }

            return userDto;
        }
    }
}
