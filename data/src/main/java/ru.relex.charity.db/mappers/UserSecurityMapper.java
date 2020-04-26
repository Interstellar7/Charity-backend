package ru.relex.charity.db.mappers;

import org.apache.ibatis.annotations.*;
import ru.relex.charity.db.models.SecurityUserDetails;
import ru.relex.common.models.Role;

import java.util.List;

@Mapper
public interface UserSecurityMapper {

    @Select(
            //language=PostgreSQL
            "SELECT " +
                    "u.user_id as id, " +
                    "u.password, " +
                    "u.username " +
                    "FROM users u " +
                    "WHERE u.deleted = false AND u.username = #{username}"
    )
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "password", column = "password"),
            @Result(property = "username", column = "username"),
            @Result(property = "roles", javaType = List.class, column = "id",
                    many = @Many(select = "findRolesByUser")
            )
    })
    SecurityUserDetails findUserByUsername(@Param("username") String username);

    @Select(
            //language=PostgreSQL
            "SELECT r.role_id, r.name FROM roles r " +
                    "JOIN user_roles ur ON ur.role_id = r.role_id " +
                    "WHERE ur.user_id = #{userId}"
    )
    List<Role> findRolesByUser(@Param("userId") int userId);

//
//    @Select(
//            // language=PostgreSQL
//            "SELECT " +
//                    "u.user_id, " +
//                    "u.username, " +
//                    "u.password "+
//                    "array_agg(ur.role_id) as roles " +
//                    "FROM users u " +
//                    "LEFT JOIN user_roles ur ON ur.user_id = u.user_id " +
//                    "WHERE u.deleted = false " +
//            " AND u.username = #{name}" +
//                    " GROUP BY u.user_id, u.username, u.password"
//    )
//    SecurityUserDetails findUserById(String name);
}
