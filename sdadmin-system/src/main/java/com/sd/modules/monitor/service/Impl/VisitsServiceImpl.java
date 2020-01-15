package com.sd.modules.monitor.service.Impl;

import com.sd.mapper.LogMapper;
import com.sd.modules.monitor.mapper.VisitsMapper;
import com.sd.modules.monitor.service.VisitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author siyang
 * @create 2020-01-15 20:50
 */
@Service
public class VisitsServiceImpl implements VisitsService {
    @Autowired
    LogMapper logMapper;
    @Autowired
    VisitsMapper visitsMapper;

    @Override
    public void save() {

    }

    @Override
    public void count(HttpServletRequest request) {

    }

    @Override
    public Object get() {
        return null;
    }

    @Override
    public Object getChartData() {
        return null;
    }
}
