package com.sd.modules.system.service;

import com.sd.modules.system.domain.Role;
import com.sd.modules.system.service.dto.MenuDto;
import com.sd.modules.system.service.dto.RoleSmallDto;

import java.util.List;
import java.util.Map;

/**
 * @author siyang
 * @create 2020-01-14 16:34
 */
public interface MenuService {

    /**
     * 根据用户 的所有对象得到 所有的菜单
     * type不等于2，且按照sort从小到大排序
     * @return
     */
    public List<MenuDto> findMenusByRoles(List<RoleSmallDto> roles);

    /**
     * 构建菜单树
     * @param menuDtos
     * @return map -> content:菜单（完成子节点构建），totalElements：菜单数量
     */
    Map<String, Object> buildTree(List<MenuDto> menuDtos);

    /**
     * 从一级菜单开始构建菜单
     * @param menuDtos
     * @return
     */
    Object buildMenus(List<MenuDto> menuDtos);
}
