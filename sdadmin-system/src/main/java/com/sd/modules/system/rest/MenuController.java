package com.sd.modules.system.rest;

import com.sd.modules.security.security.JwtTokenProvider;
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
    JwtTokenProvider jwtTokenProvider;

    @ApiOperation("获取前端所需菜单")
    @GetMapping("/build")
    public ResponseEntity buildMenus(HttpServletRequest request){
        String token = jwtTokenProvider.getToken(request);
        String username = jwtTokenProvider.parseToken(token);

        return new ResponseEntity(HttpStatus.OK);

    }
}
