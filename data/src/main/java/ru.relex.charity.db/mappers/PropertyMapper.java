package ru.relex.charity.db.mappers;

import org.apache.ibatis.annotations.*;
import ru.relex.charity.db.models.Category;
import ru.relex.charity.db.models.Property;

import java.util.List;

@Mapper
public interface PropertyMapper {
    @Select(
            //language=PostgreSQL
            "SELECT " +
                    "p.property_id as id, " +
                    "p.name " +
                    "FROM properties p " +
                    "WHERE (#{search:VARCHAR} IS NULL) OR (p.name LIKE CONCAT('%', #{search:VARCHAR}, '%'))"
    )
    List<Property> getProperties(@Param("search") String search);

    @Select(
            //language=PostgreSQL
            "SELECT " +
                    "p.property_id as id, " +
                    "p.name " +
                    "FROM properties p " +
                    "WHERE p.property_id = #{id}"
    )
    Property getPropertyById(@Param("id") int id);

    @Insert(
            "INSERT INTO properties (name) "+
                    "VALUES (#{name})"
    )
    @SelectKey(
            before = false,
            keyProperty = "id",
            keyColumn = "property_id",
            statement = "select currval('properties_property_id_seq')",
            resultType = Integer.class
    )
    void insert(Property property);

    @Update(
            "UPDATE properties SET " +
                    "name = #{name} " +
                    "WHERE property_id = #{id}"
    )
    void update(Category category);

    @Delete("DELETE FROM properties " +
            "WHERE property_id = #{id}")
    void delete(@Param("id") int id);
}
