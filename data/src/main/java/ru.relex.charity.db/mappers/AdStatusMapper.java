package ru.relex.charity.db.mappers;

import org.apache.ibatis.annotations.*;
import ru.relex.charity.db.models.AdStatus;

import java.util.List;

@Mapper
public interface AdStatusMapper {
    @Select(
            //language=PostgreSQL
            "SELECT " +
                    "ads.status_id as id, " +
                    "ads.name " +
                    "FROM ad_statuses ads " +
                    "WHERE (#{search:VARCHAR} IS NULL) OR (ads.name LIKE CONCAT('%', #{search:VARCHAR}, '%'))"
    )
    List<AdStatus> getAdStatuses(@Param("search") String search);

    @Select(
            //language=PostgreSQL
            "SELECT " +
                    "ads.status_id as id, " +
                    "ads.name " +
                    "FROM ad_statuses ads " +
                    "WHERE ads.status_id = #{id}"
    )
    AdStatus getAdStatusById(@Param("id") int id);
}
