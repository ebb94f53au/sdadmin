package com.sd;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.sd.modules.system.domain.Job;
import com.sd.modules.system.domain.User;
import com.sd.modules.system.mapper.RoleMapper;
import com.sd.modules.system.mapper.UserMapper;
import com.sd.modules.system.service.UserService;
import com.sd.modules.system.service.converter.JobConverter;
import com.sd.modules.system.service.dto.DeptDto;
import com.sd.modules.system.service.dto.JobDto;
import com.sd.modules.system.service.dto.UserDto;
import com.sd.utils.SpringContextHolder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author siyang
 * @create 2020-01-12 12:18
 */

@RestController
@SpringBootApplication
public class AppRun {
    @Autowired
    private UserService userService;



    public static void main(String[] args) {
        SpringApplication.run(AppRun.class);
    }



    @GetMapping("/")
    public String index(){
        UserDto admin = userService.getUserByUsername("admin");
        System.out.println(admin);

        return "11";
    }

}
