package com.sd.modules.monitor.domain;

public class MonitorServer {
    private Integer id;

    private Integer cpuCore;

    private Double cpuRate;

    private Double diskTotal;

    private Double diskUsed;

    private Double memTotal;

    private Double memUsed;

    private String name;

    private Integer port;

    private Integer sort;

    private String state;

    private Double swapTotal;

    private Double swapUsed;

    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCpuCore() {
        return cpuCore;
    }

    public void setCpuCore(Integer cpuCore) {
        this.cpuCore = cpuCore;
    }

    public Double getCpuRate() {
        return cpuRate;
    }

    public void setCpuRate(Double cpuRate) {
        this.cpuRate = cpuRate;
    }

    public Double getDiskTotal() {
        return diskTotal;
    }

    public void setDiskTotal(Double diskTotal) {
        this.diskTotal = diskTotal;
    }

    public Double getDiskUsed() {
        return diskUsed;
    }

    public void setDiskUsed(Double diskUsed) {
        this.diskUsed = diskUsed;
    }

    public Double getMemTotal() {
        return memTotal;
    }

    public void setMemTotal(Double memTotal) {
        this.memTotal = memTotal;
    }

    public Double getMemUsed() {
        return memUsed;
    }

    public void setMemUsed(Double memUsed) {
        this.memUsed = memUsed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Double getSwapTotal() {
        return swapTotal;
    }

    public void setSwapTotal(Double swapTotal) {
        this.swapTotal = swapTotal;
    }

    public Double getSwapUsed() {
        return swapUsed;
    }

    public void setSwapUsed(Double swapUsed) {
        this.swapUsed = swapUsed;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}