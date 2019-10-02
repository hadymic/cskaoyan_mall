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
@MappedTypes(Byte[].class)
public class TransferByteArrayHandler implements TypeHandler<Byte[]> {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Byte[] bytes, JdbcType jdbcType) throws SQLException {
        try {
            String jsonArray = objectMapper.writeValueAsString(bytes);
            preparedStatement.setString(i,jsonArray);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Byte[] getResult(ResultSet resultSet, String s) throws SQLException {
        String value = resultSet.getString(s);
        return parseString2ByteArray(value);
    }

    @Override
    public Byte[] getResult(ResultSet resultSet, int i) throws SQLException {
        String value = resultSet.getString(i);
        return parseString2ByteArray(value);
    }

    private Byte[] parseString2ByteArray(String value) {
        Byte[] bytes = new Byte[0];
        if (value == null) {
            return bytes;
        }
        try {
          bytes = objectMapper.readValue(value, Byte[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    @Override
    public Byte[] getResult(CallableStatement callableStatement, int i) throws SQLException {
        String value = callableStatement.getString(i);

        return parseString2ByteArray(value);
    }
}
