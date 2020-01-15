package com.sd.modules.monitor.domain;

import java.util.Date;

public class Visits {
    private Long id;

    private Date createTime;

    private String date;

    private Long ipCounts;

    private Long pvCounts;

    private String weekDay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public Long getIpCounts() {
        return ipCounts;
    }

    public void setIpCounts(Long ipCounts) {
        this.ipCounts = ipCounts;
    }

    public Long getPvCounts() {
        return pvCounts;
    }

    public void setPvCounts(Long pvCounts) {
        this.pvCounts = pvCounts;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay == null ? null : weekDay.trim();
    }
}