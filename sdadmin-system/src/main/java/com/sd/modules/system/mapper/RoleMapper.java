package com.sd.modules.system.mapper;

import com.sd.modules.system.domain.Menu;
import com.sd.modules.system.domain.Role;
import com.sd.modules.system.domain.RoleExample;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleMapper {
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * 根据roleId获得全部权限
     * @param roleId
     * @return
     */
    Set<String> getPermissionsByRoleId(Long roleId);
    /**
     * 根据roleId获得全部菜单
     * type不等于2，且按照sort从小到大排序
     * @param roleId
     * @return
     */
    Set<Menu> getMenusByRoleId(Long roleId);
}