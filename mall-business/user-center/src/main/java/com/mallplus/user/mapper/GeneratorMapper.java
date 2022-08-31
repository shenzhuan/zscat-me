package com.mallplus.user.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface GeneratorMapper {
    @Select("select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables"
            + " where table_schema = (select database())")
    List<Map<String, Object>> list();

    @Select("select count(*) from information_schema.tables where table_schema = (select database())")
    int count(Map<String, Object> map);

    @Select("select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables \r\n"
            + "	where table_schema = (select database()) and table_name = #{tableName}")
    Map<String, String> get(String tableName);

    @Select("select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey, extra from information_schema.columns\r\n"
            + " where table_name = #{tableName} and table_schema = (select database()) order by ordinal_position")
    List<Map<String, String>> listColumns(String tableName);
}
