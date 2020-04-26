package ru.relex.charity.db.handlers;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import ru.relex.common.models.Role;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(Role.class)
@MappedJdbcTypes({JdbcType.INTEGER, JdbcType.BIGINT})
public class RoleTypeHandler implements TypeHandler<Role> {
    @Override
    public void setParameter(PreparedStatement ps, int i, Role parameter, JdbcType jdbcType) throws SQLException {
        if (parameter == null) {
            ps.setNull(i, jdbcType.TYPE_CODE);
        } else {
            ps.setInt(i, parameter.getId());
        }
    }

    @Override
    public Role getResult(ResultSet rs, String columnName) throws SQLException {
        return Role.of(rs.getInt(columnName)).orElse(null);
    }

    @Override
    public Role getResult(ResultSet rs, int columnIndex) throws SQLException {
        return Role.of(rs.getInt(columnIndex)).orElse(null);
    }

    @Override
    public Role getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return Role.of(cs.getInt(columnIndex)).orElse(null);
    }
}
