package com.sd.rest;

import com.sd.domain.ColumnConfig;
import com.sd.domain.GenConfig;
import com.sd.mapper.GenConfigMapper;
import com.sd.service.GenConfigService;
import com.sd.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author siyang
 * @create 2020-01-13 17:44
 */
@RequestMapping("/gen")
@RestController
public class TestController {
    @Autowired
    GeneratorService generatorService;
    @Autowired
    GenConfigService genConfigService;


    @GetMapping("/doit")
    public void doit(){
        generatorService.generator(genConfigService.find("gen_test"),generatorService.getColumns("gen_test"));
//        genConfigMapper.findByTableName("user");
    }
}
