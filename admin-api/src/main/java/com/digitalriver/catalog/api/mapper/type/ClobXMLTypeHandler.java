package com.digitalriver.catalog.api.mapper.type;

import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ClobXMLTypeHandler extends AbstractXMLTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Map<String, ?> parameter, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public Map<String, ?> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return this.handle(this.handleBytes(rs.getBytes(columnName)));
    }

    @Override
    public Map<String, ?> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return this.handle(this.handleBytes(rs.getBytes(columnIndex)));
    }

    @Override
    public Map<String, ?> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return this.handle(this.handleBytes(cs.getBytes(columnIndex)));
    }

    protected String handleBytes(final byte[] data) throws SQLException {
        final String result = data == null ? "" : new String(data);
        int headerIndex = result.indexOf("<?xml");
        return result.substring(headerIndex < 0 ? 0 : headerIndex);
    }


}
