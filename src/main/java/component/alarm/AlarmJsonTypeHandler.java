package component.alarm;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.json.JSONObject;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// alarm message 는 alarm message 의 JSON data 자체를 DB 에 저장한다. json data 를 저장하기위한 handler class.
public class AlarmJsonTypeHandler extends BaseTypeHandler<Object> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        String p = JacksonParsing.toString(parameter);
        ps.setObject(i, p);
    }

    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Object d = rs.getObject(columnName);
        return JacksonParsing.toMap(d.toString());
    }

    @Override
    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Object d = rs.getObject(columnIndex);
        return JacksonParsing.toMap(d.toString());
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Object d = cs.getObject(columnIndex);
        return JacksonParsing.toMap(d.toString());
    }

}
