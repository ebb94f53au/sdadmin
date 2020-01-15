package com;


import com.sd.modules.system.domain.Job;
import com.sd.modules.system.service.dto.JobDto;

import java.util.Date;

/**
 * @author siyang
 * @create 2020-01-12 21:09
 */
public class Test {
    public static void main(String[] args) {
        Job job =new Job((long)1,"业务处理",true,(long)1,(long)1,new Date());
    }
}
