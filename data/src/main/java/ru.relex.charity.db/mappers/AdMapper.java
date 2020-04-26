package ru.relex.charity.db.mappers;

import org.apache.ibatis.annotations.*;
import ru.relex.charity.db.models.Ad;

import java.util.List;

@Mapper
public interface AdMapper {
    @Select(
            //language=PostgreSQL
            "SELECT " +
                    "a.ad_id as id," +
                    "a.title, " +
                    "a.description, " +
                    "a.category_id, " +
                    "a.ad_status_id, " +
                    "a.expired_at " +
                    "FROM ads a " +
                    "WHERE a.deleted = false " +
                    "AND (#{search:VARCHAR} IS NULL) OR (a.title LIKE CONCAT('%', #{search:VARCHAR}, '%'))"
    )
    List<Ad> getAds(@Param("search") String search);

    @Select(
            //language=PostgreSQL
            "SELECT " +
                    "a.ad_id as id, " +
                    "a.title, " +
                    "a.description, " +
                    "a.category_id, " +
                    "a.ad_status_id, " +
                    "a.expired_at " +
                    "FROM ads a " +
                    "WHERE a.deleted = false  AND c.ad_id = #{id}"
    )
    Ad getAdById(@Param("id") int id);

    @Insert(
            "INSERT INTO ads (title, description, category_id, ad_status_id, created_at, created_by) "+
                    "VALUES (#{title}, #{description}, #{category_id}, #{ad_status_id}, CURRENT_TIMESTAMP(), #{createdBy})"
    )
    @SelectKey(
            before = false,
            keyProperty = "id",
            keyColumn = "ad_id",
            statement = "select currval('ads_ad_id_seq')",
            resultType = Integer.class
    )
    void insert(Ad ad);

    @Update(
            "UPDATE ads SET " +
                    "title = #{title}, " +
                    "description = #{description}, " +
                    "category_id = #{categoryId}, " +
                    "ad_status_id = #{adStatusId}, " +
                    "updated_at = CURRENT_TIMESTAMP(), " +
                    "updated_by = #{updatedBy} " +
                    "WHERE ad_id = #{id}"
    )
    void update(Ad ad);

    @Delete("UPDATE a SET " +
            "deleted = true, " +
            "updated_at = CURRENT_TIMESTAMP(), " +
            "updated_by = #{updatedBy} " +
            "WHERE ad_id = #{id}")
    void delete(@Param("id") int id);
}
