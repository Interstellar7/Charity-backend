package ru.relex.charity.db.mappers;

import org.apache.ibatis.annotations.*;
import ru.relex.charity.db.models.User;
import ru.relex.common.models.Role;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    @Select(
            //language=PostgreSQL
            "SELECT " +
                    "u.user_id as id, " +
                    "u.first_name, " +
                    "u.last_name, " +
                    "u.email, " +
                    "u.username " +
                    "FROM users u " +
                    "WHERE u.deleted = false " +
                    "AND (#{search:VARCHAR} IS NULL OR CONCAT_WS('$', first_name, last_name, username) LIKE CONCAT('%', #{search:VARCHAR}, '%'))"
    )
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "username", column = "username"),
            @Result(property = "roles", javaType = List.class, column = "id",
                    many = @Many(select = "findRolesByUser")
            )
    })
    List<User> getUsers(@Param("search") String search);

    @Select(
            //language=PostgreSQL
            "SELECT " +
                    "u.user_id as id, " +
                    "u.first_name, " +
                    "u.last_name, " +
                    "u.email, " +
                    "u.username " +
                    "FROM users u " +
                    "WHERE u.deleted = false AND u.user_id = #{id}"
    )
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "username", column = "username"),
            @Result(property = "roles", javaType = List.class, column = "id",
                    many = @Many(select = "findRolesByUser")
            )
    })
    User getUserById(@Param("id") int id);

    @Select(
            //language=PostgreSQL
            "SELECT r.role_id, r.name FROM roles r " +
                    "JOIN user_roles ur ON ur.role_id = r.role_id " +
                    "WHERE ur.user_id = #{userId}"
    )
    List<Role> findRolesByUser(@Param("userId") int userId);

    @Insert(
            //language=PostgreSQL
            "INSERT INTO users (first_name, last_name, email, username, password, created_at, created_by) " +
                    "VALUES (#{firstName}, #{lastName}, #{email}, #{username}, #{password}, CURRENT_TIMESTAMP, #{createdBy})"
    )
    @SelectKey(
            before = false,
            keyProperty = "id",
            keyColumn = "user_id",
            statement = "select currval('users_user_id_seq')",
            resultType = Integer.class
    )
    void insert(User user);

    @Update(
            //language=PostgreSQL
            "UPDATE users SET " +
                    "first_name = #{firstName}, " +
                    "last_name = #{lastName}, " +
                    "email = #{email}, " +
                    "updated_at = CURRENT_TIMESTAMP, " +
                    "updated_by = #{updatedBy} " +
                    "WHERE user_id = #{id}"
    )
    void update(User user);

    @Update(
            //language=PostgreSQL
            "UPDATE users SET " +
                    "deleted = true, " +
                    "updated_at = CURRENT_TIMESTAMP, " +
//                   "updated_by = #{updatedBy} " + // todo обновлять значением текущего пользователя
                    "WHERE user_id = #{id}"
    )
    void delete(@Param("id") int id);

    @Select(
            //language=PostgreSQL
            "SELECT 1 " +
                    "FROM users u " +
                    "WHERE u.username = #{username}"
    )
    Optional<Boolean> existUsername(@Param("username") String username);

    @Select(
            //language=PostgreSQL
            "SELECT 1 " +
                    "FROM users u " +
                    "WHERE u.email = #{email}"
    )
    Optional<Boolean> existEmail(@Param("email") String email);

    @Select(
            //language=PostgreSQL
            "SELECT 1 " +
                    "FROM users u " +
                    "WHERE u.email = #{email} AND u.user_id != #{id}"
    )
    Optional<Boolean> existEmailExceptUser(@Param("email") String email, @Param("id") Integer id);

}
