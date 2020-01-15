package com.sd.modules.system.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
* @author Zheng Jie
* @date 2019-03-25
 * 需要另外添加属性为
 *
*/
@Data
public class DeptDto implements Serializable {

    private Long id;

    private String name;

    @NotNull
    private Boolean enabled;

    private Long pid;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<DeptDto> children;

    private Date createTime;

    public String getLabel() {
        return name;
    }
}