package com.sd.modules.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.sd.modules.system.domain.Menu;
import com.sd.modules.system.domain.vo.MenuMetaVo;
import com.sd.modules.system.domain.vo.MenuVo;
import com.sd.modules.system.mapper.MenuMapper;
import com.sd.modules.system.mapper.RoleMapper;
import com.sd.modules.system.service.MenuService;
import com.sd.modules.system.service.converter.MenuConverter;
import com.sd.modules.system.service.dto.MenuDto;
import com.sd.modules.system.service.dto.RoleSmallDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author siyang
 * @create 2020-01-14 16:35
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuConverter menuConverter;

    @Override
    public List<MenuDto> findMenusByRoles(List<RoleSmallDto> roles) {
        List<MenuDto> menuDtos =new ArrayList<>();
        //遍历所有角色
        roles.forEach(role->{
            if(role!=null){
                Set<Menu> menus = roleMapper.getMenusByRoleId(role.getId());
                //遍历得到所有的菜单
                menus.forEach(menu->{
                    menuDtos.add(menuConverter.toDto(menu));
                });
            }
        });
        return menuDtos;
    }


    @Override
    public Map<String, Object> buildTree(List<MenuDto> menuDtos) {
        List<MenuDto> trees = new ArrayList<>();
        Set<Long> ids = new HashSet<>();
        for (MenuDto menuDTO : menuDtos) {
            //获得一级目录
            if (menuDTO.getPid() == 0) {
                trees.add(menuDTO);
            }
            //循环遍历，找到子节点菜单
            for (MenuDto it : menuDtos) {
                if (it.getPid().equals(menuDTO.getId())) {
                    if (menuDTO.getChildren() == null) {
                        menuDTO.setChildren(new ArrayList<>());
                    }
                    menuDTO.getChildren().add(it);
                    ids.add(it.getId());
                }
            }
        }
        Map<String,Object> map = new HashMap<>(2);
        if(trees.size() == 0){
            //找到从来没成为子节点的菜单节点
            trees = menuDtos.stream().filter(s -> !ids.contains(s.getId())).collect(Collectors.toList());
        }
        map.put("content",trees);
        map.put("totalElements", menuDtos.size());
        return map;
    }

    @Override
    public List<MenuVo> buildMenus(List<MenuDto> menuDtos) {
        List<MenuVo> list =new ArrayList<>();
        menuDtos.forEach(menuDto->{
            if(menuDto!=null){
                //子节点可以后续递归
                List<MenuDto> children = menuDto.getChildren();
                MenuVo menuVo = new MenuVo();
                //如果 componentName不为空就用componentName 否则用Name
                menuVo.setName(ObjectUtil.isNotEmpty(menuDto.getComponentName())  ? menuDto.getComponentName() : menuDto.getName());
                //一级菜单 需要加斜杠，不然会警告
                menuVo.setPath(menuDto.getPid() == 0 ? "/" + menuDto.getPath() :menuDto.getPath());
                menuVo.setHidden(menuDto.getHidden());

                //如果不是外链
                if(!menuDto.getIFrame()){
                    //如果是一级目录
                    if(menuDto.getPid() == 0){
                        //有Component 就使用 没有就使用LayOut
                        menuVo.setComponent(StrUtil.isEmpty(menuDto.getComponent())?"Layout":menuDto.getComponent());
                    //如果不是一级目录且有Component
                    }else if(!StrUtil.isEmpty(menuDto.getComponent())){
                        menuVo.setComponent(menuDto.getComponent());
                    }

                }
                //设置meta
                menuVo.setMeta(new MenuMetaVo(menuDto.getName(),menuDto.getIcon(),!menuDto.getCache()));
                //如果子节点不为空
                if(children !=null && children.size()!=0){
                    //总是可见
                    menuVo.setAlwaysShow(true);
                    //没重定向
                    menuVo.setRedirect("noredirect");
                    //递归设置子节点
                    menuVo.setChildren(buildMenus(children));
                //处理是一级菜单并且没有子菜单的情况
                }else if(menuDto.getPid() == 0){
                    MenuVo menuVo1 = new MenuVo();
                    menuVo1.setMeta(menuVo.getMeta());
                    // 非外链
                    if(!menuDto.getIFrame()){
                        menuVo1.setPath("index");
                        menuVo1.setName(menuVo.getName());
                        menuVo1.setComponent(menuVo.getComponent());
                    } else {
                        menuVo1.setPath(menuDto.getPath());
                    }
                    menuVo.setName(null);
                    menuVo.setMeta(null);
                    menuVo.setComponent("Layout");
                    List<MenuVo> list1 = new ArrayList<>();
                    list1.add(menuVo1);
                    menuVo.setChildren(list1);

                }
                list.add(menuVo);
            }


        });


        return list;
    }
}
