package ru.relex.charity.db.mappers;

import org.apache.ibatis.annotations.*;
import ru.relex.common.models.Role;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    @Select(
            // language=PostgreSQL
            "SELECT r.role_id, r.name FROM roles r " +
                    "JOIN user_roles ur ON ur.role_id = r.role_id " +
                    "WHERE ur.user_id = #{userId}"
    )
    List<Role> findRolesByUser(int userId);

    @Insert(
            // language=PostgreSQL
            "INSERT INTO user_roles (user_id, role_id) VALUES (#{userId}, #{roleId})"
    )
    void assign(@Param("userId") int userId, @Param("roleId") int roleId);

    @Delete(
            // language=PostgreSQL
            "DELETE FROM user_roles WHERE user_id = #{userId} AND role_id = #{roleId}"
    )
    void resign(@Param("userId") int userId, @Param("roleId") int roleId);
}
