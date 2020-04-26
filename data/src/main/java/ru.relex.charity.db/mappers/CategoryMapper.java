package ru.relex.charity.db.mappers;

import org.apache.ibatis.annotations.*;
import ru.relex.charity.db.models.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Select(
            //language=PostgreSQL
            "SELECT " +
                    "c.category_id as id, " +
                    "c.name, " +
                    "c.parent_id, " +
                    "c.points, " +
                    "EXISTS ( SELECT 1 FROM categories TMP where TMP.parent_id = c.category_id  AND TMP.category_id != TMP.parent_id LIMIT 1) as has_children " +
                    "FROM categories c " +
                    "WHERE c.deleted = false " +
                    "AND ((#{parentId:INTEGER} IS NULL AND c.category_id = c.parent_id) OR (c.parent_id = #{parentId:INTEGER} AND c.category_id != c.parent_id)) " +
                    "AND ((#{search:VARCHAR} IS NULL) OR (c.name LIKE CONCAT('%', #{search:VARCHAR}, '%')))"
    )
    List<Category> getCategories(@Param("search") String search, @Param("parentId") Integer parentId);

    @Select(
            //language=PostgreSQL
            "SELECT " +
                    "c.category_id as id, " +
                    "c.name, " +
                    "c.parent_id, " +
                    "c.points, " +
                    "EXISTS ( SELECT 1 FROM categories TMP where TMP.parent_id = c.category_id AND TMP.category_id != TMP.parent_id LIMIT 1) as has_children " +
                    "FROM categories c " +
                    "WHERE c.deleted = false " +
                    "AND c.category_id = #{id}"
    )
    Category getCategoryById(@Param("id") int id);

    @Insert(
            "INSERT INTO categories (name, parent_id, created_at, created_by) "+
                    "VALUES (#{name}, #{parentId}, CURRENT_TIMESTAMP(), #{createdBy})"
    )
    @SelectKey(
            before = false,
            keyProperty = "id",
            keyColumn = "category_id",
            statement = "select currval('categories_category_id_seq')",
            resultType = Integer.class
    )
    void insert(Category category);

    @Update(
            "UPDATE categories SET " +
                    "name = #{name}, " +
                    "parent_id = #{parentId}, " +
                    "points = #{points}, " +
                    "updated_at = CURRENT_TIMESTAMP(), " +
                    "updated_by = #{updatedBy} " +
                    "WHERE category_id = #{id}"
    )
    void update(Category category);

    @Delete(
            "UPDATE categories SET " +
            "deleted = true, " +
            "updated_at = CURRENT_TIMESTAMP(), " +
            "updated_by = #{updatedBy} " +
            "WHERE category_id = #{id}")
    void delete(@Param("id") int id);
}
