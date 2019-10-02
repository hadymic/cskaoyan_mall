package com.cskaoyan.mall.typehandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库String数组互转
 */
@MappedTypes(String[].class)
public class TransferStringArrayHandler implements TypeHandler<String[]> {
    private ObjectMapper objectMapper = new ObjectMapper();

    //插入数据 由javabean转换为数据库接收的类型
    @Override
    public void setParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType) throws SQLException {
        try {
            String jsonArray = objectMapper.writeValueAsString(parameter);
            ps.setString(i, jsonArray);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    //由数据中查询出的结果转换成javabean中的类型
    @Override
    public String[] getResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return parseString2StringArray(value);
    }

    private String[] parseString2StringArray(String value) {
        String[] strings = new String[0];
        if (value == null) {
            return strings;
        }
        try {
            strings = objectMapper.readValue(value, String[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }

    @Override
    public String[] getResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return parseString2StringArray(value);
    }

    @Override
    public String[] getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return parseString2StringArray(value);
    }
}
