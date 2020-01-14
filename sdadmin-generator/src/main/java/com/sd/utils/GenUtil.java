package com.sd.utils;

import cn.hutool.core.util.StrUtil;
import com.sd.domain.ColumnConfig;
import com.sd.domain.GenConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author siyang
 * @create 2020-01-13 17:55
 * 生成代码类
 */
@Slf4j
public class GenUtil {
    private static final String TIMESTAMP = "Timestamp";

    private static final String BIGDECIMAL = "BigDecimal";

    public static final String PK = "PRI";

    public static final String EXTRA = "auto_increment";

    /**
     * 生成代码
     * @param columns
     * @param genConfig
     */
    public static void generatorCode(List<ColumnConfig> columns, GenConfig genConfig) throws IOException {
        Map<String, Object> genMap = getGenMap(columns,genConfig);


    }

    // 获取模版数据
    private static Map<String,Object> getGenMap(List<ColumnConfig> columnInfos, GenConfig genConfig) {
        // 存储模版字段数据
        Map<String,Object> genMap = new HashMap<>(16);
        // 接口别名
        genMap.put("apiAlias",genConfig.getApiAlias());
        // 包名称
        genMap.put("package",genConfig.getPack());
        // 模块名称
        genMap.put("moduleName",genConfig.getModuleName());
        // 作者
        genMap.put("author",genConfig.getAuthor());
        // 创建日期
        genMap.put("date", LocalDate.now().toString());
        // 表名
        genMap.put("tableName",genConfig.getTableName());
        // 大写开头的类名
        String className = StringUtils.toCapitalizeCamelCase(genConfig.getTableName());
        // 小写开头的类名
        String changeClassName = StringUtils.toCamelCase(genConfig.getTableName());
        // 判断是否去除表前缀
        if (StringUtils.isNotEmpty(genConfig.getPrefix())) {
            className = StringUtils.toCapitalizeCamelCase(StrUtil.removePrefix(genConfig.getTableName(),genConfig.getPrefix()));
            changeClassName = StringUtils.toCamelCase(StrUtil.removePrefix(genConfig.getTableName(),genConfig.getPrefix()));
        }
        // 保存类名
        genMap.put("className", className);
        // 保存小写开头的类名
        genMap.put("changeClassName", changeClassName);
        // 存在 Timestamp 字段
        genMap.put("hasTimestamp",false);
        // 查询类中存在 Timestamp 字段
        genMap.put("queryHasTimestamp",false);
        // 存在 BigDecimal 字段
        genMap.put("hasBigDecimal",false);
        // 查询类中存在 BigDecimal 字段
        genMap.put("queryHasBigDecimal",false);
        // 是否需要创建查询
        genMap.put("hasQuery",false);
        // 自增主键
        genMap.put("auto",false);
        // 存在字典
        genMap.put("hasDict",false);
        // 存在日期注解
        genMap.put("hasDateAnnotation",false);
        // 保存字段信息
        List<Map<String,Object>> columns = new ArrayList<>();
        // 保存查询字段的信息
        List<Map<String,Object>> queryColumns = new ArrayList<>();
        // 存储字典信息
        List<String> dicts = new ArrayList<>();
        // 存储 between 信息
        List<Map<String,Object>> betweens = new ArrayList<>();
        // 存储不为空的字段信息
        List<Map<String,Object>> isNotNullColumns = new ArrayList<>();

        for (ColumnConfig column : columnInfos) {
            Map<String,Object> listMap = new HashMap<>(16);
            // 字段描述
            listMap.put("remark",column.getRemark());
            // 字段类型
            listMap.put("columnKey",column.getKeyType());
            // 主键类型
            String colType = ColUtil.cloToJava(column.getColumnType());
            // 小写开头的字段名
            String changeColumnName = StringUtils.toCamelCase(column.getColumnName().toString());
            // 大写开头的字段名
            String capitalColumnName = StringUtils.toCapitalizeCamelCase(column.getColumnName().toString());
            if(PK.equals(column.getKeyType())){
                // 存储主键类型
                genMap.put("pkColumnType",colType);
                // 存储小写开头的字段名
                genMap.put("pkChangeColName",changeColumnName);
                // 存储大写开头的字段名
                genMap.put("pkCapitalColName",capitalColumnName);
            }
            // 是否存在 Timestamp 类型的字段
            if(TIMESTAMP.equals(colType)){
                genMap.put("hasTimestamp",true);
            }
            // 是否存在 BigDecimal 类型的字段
            if(BIGDECIMAL.equals(colType)){
                genMap.put("hasBigDecimal",true);
            }
            // 主键是否自增
            if(EXTRA.equals(column.getExtra())){
                genMap.put("auto",true);
            }
            // 主键存在字典
            if(StringUtils.isNotBlank(column.getDictName())){
                genMap.put("hasDict",true);
                dicts.add(column.getDictName());
            }

            // 存储字段类型
            listMap.put("columnType",colType);
            // 存储字原始段名称
            listMap.put("columnName",column.getColumnName());
            // 不为空
            listMap.put("istNotNull",column.getNotNull());
            // 字段列表显示
            listMap.put("columnShow",column.getListShow());
            // 表单显示
            listMap.put("formShow",column.getFormShow());
            // 表单组件类型
            listMap.put("formType", StringUtils.isNotBlank(column.getFormType()) ? column.getFormType() : "Input");
            // 小写开头的字段名称
            listMap.put("changeColumnName",changeColumnName);
            //大写开头的字段名称
            listMap.put("capitalColumnName",capitalColumnName);
            // 字典名称
            listMap.put("dictName",column.getDictName());
            // 日期注解
            listMap.put("dateAnnotation",column.getDateAnnotation());
            if(StringUtils.isNotBlank(column.getDateAnnotation())){
                genMap.put("hasDateAnnotation",true);
            }
            // 添加非空字段信息
            if(column.getNotNull()){
                isNotNullColumns.add(listMap);
            }
            // 判断是否有查询，如有则把查询的字段set进columnQuery
            if(!StringUtils.isBlank(column.getQueryType())){
                // 查询类型
                listMap.put("queryType",column.getQueryType());
                // 是否存在查询
                genMap.put("hasQuery",true);
                if(TIMESTAMP.equals(colType)){
                    // 查询中存储 Timestamp 类型
                    genMap.put("queryHasTimestamp",true);
                }
                if(BIGDECIMAL.equals(colType)){
                    // 查询中存储 BigDecimal 类型
                    genMap.put("queryHasBigDecimal",true);
                }
                if("between".equalsIgnoreCase(column.getQueryType())){
                    betweens.add(listMap);
                } else {
                    // 添加到查询列表中
                    queryColumns.add(listMap);
                }
            }
            // 添加到字段列表中
            columns.add(listMap);
        }
        // 保存字段列表
        genMap.put("columns",columns);
        // 保存查询列表
        genMap.put("queryColumns",queryColumns);
        // 保存字段列表
        genMap.put("dicts",dicts);
        // 保存查询列表
        genMap.put("betweens",betweens);
        // 保存非空字段信息
        genMap.put("isNotNullColumns",isNotNullColumns);
        return genMap;
    }
}