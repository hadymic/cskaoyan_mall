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
 * 数据库Byte数组互转
 */
@MappedTypes(Integer[].class)
public class TransferIntegerArrayHandler implements TypeHandler<Integer[]> {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Integer[] bytes, JdbcType jdbcType) throws SQLException {
        try {
            String jsonArray = objectMapper.writeValueAsString(bytes);
            preparedStatement.setString(i, jsonArray);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer[] getResult(ResultSet resultSet, String s) throws SQLException {
        String value = resultSet.getString(s);
        return parseString2ByteArray(value);
    }

    @Override
    public Integer[] getResult(ResultSet resultSet, int i) throws SQLException {
        String value = resultSet.getString(i);
        return parseString2ByteArray(value);
    }

    private Integer[] parseString2ByteArray(String value) {
        Integer[] integers = new Integer[0];
        if (value == null) {
            return integers;
        }
        try {
            integers = objectMapper.readValue(value, Integer[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return integers;
    }

    @Override
    public Integer[] getResult(CallableStatement callableStatement, int i) throws SQLException {
        String value = callableStatement.getString(i);
        return parseString2ByteArray(value);
    }
}
