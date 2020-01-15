package com.sd.modules.system.rest;

import com.sd.modules.security.security.JwtTokenProvider;
import com.sd.modules.system.service.MenuService;
import com.sd.modules.system.service.RoleService;
import com.sd.modules.system.service.dto.MenuDto;
import com.sd.modules.system.service.dto.RoleSmallDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author siyang
 * @create 2020-01-14 16:33
 */
@Slf4j
@Api(tags = "系统：菜单管理")
@RestController
@RequestMapping("/api/menus")
public class MenuController {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    @ApiOperation("获取前端所需菜单")
    @GetMapping("/build")
    public ResponseEntity buildMenus(HttpServletRequest request){
        //根据request获得token 和username
        String token = jwtTokenProvider.getToken(request);
        String username = jwtTokenProvider.parseToken(token);
        //获得所有的菜单
        List<MenuDto> MenuDtos = menuService.findMenusByRoles(roleService.findRolesByUsername(username));
        List<MenuDto> menuDto = (List<MenuDto>)menuService.buildTree(MenuDtos).get("content");
        return new ResponseEntity(menuService.buildMenus(menuDto),HttpStatus.OK);

    }
}
