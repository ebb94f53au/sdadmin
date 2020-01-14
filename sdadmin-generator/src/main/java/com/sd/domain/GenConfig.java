package com.sd.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author siyang
 * @create 2020-01-13 16:15
 * 表属性
 */
@NoArgsConstructor
@Data
public class GenConfig {

    public GenConfig(String tableName) {
        this.cover = false;
        this.moduleName = "sdadmin-system";
        this.tableName = tableName;
    }
    private Long id;

    @NotBlank
    private String tableName;

    /** 接口名称 **/
    private String apiAlias;

    /** 包路径 */
    @NotBlank
    private String pack;

    /** 模块名 */
    @NotBlank
    private String moduleName;

    /** 前端文件路径 */
    @NotBlank
    private String path;

    /** 前端文件路径 */
    private String apiPath;

    /** 作者 */
    private String author;

    /** 表前缀 */
    private String prefix;

    /** 是否覆盖 */
    private Boolean cover;


}
