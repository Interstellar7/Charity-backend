package ru.relex.charity.db.mappers;

import org.apache.ibatis.annotations.*;
import ru.relex.charity.db.models.Participant;

import java.util.List;

@Mapper
public interface ParticipantMapper {
    @Select(
            // language=PostgreSQL
            "SELECT " +
                    "auction_id, " +
                    "user_id, " +
                    "message_to_giver, " +
                    "submit_at " +
                    "FROM participants " +
                    "WHERE auction_id = #{auctionId} " +
                    "AND user_id = #{userId} ;"
    )
    Participant getParticipant(@Param("auctionId") int auctionId, @Param("userId") int userId);

    @Select(
            // language=PostgreSQL
            "SELECT " +
                    "auction_id, " +
                    "user_id, " +
                    "message_to_giver, " +
                    "submit_at " +
                    "FROM participants " +
                    "WHERE auction_id = #{auctionId};"
    )
    List<Participant> getParticipantsByAuctionId(int auctionId);

    @Insert(
            // language=PostgreSQL
            "INSERT INTO participants " +
                    "(auction_id, user_id, message_to_giver, submit_at) " +
                    "VALUES (#{auctionId}, #{userId}, #{messageToGiver}, CURRENT_TIMESTAMP); "
    )
    void insertParticipant(
            @Param("auctionId") int auctionId,
            @Param("userId") int userId,
            @Param("messageToGiver") String messageToGiver
    );

    @Delete(
            //language=PostgreSQL
            "DELETE from participants " +
                    "WHERE user_id = #{userId} " +
                    "AND auction_id = #{auctionId};"
    )
    void deleteParticipant(@Param("auctionId") int auctionId, @Param("userId") int userId);

    @Select(
            "SELECT auction_id " +
            "FROM public.participants " +
            "WHERE user_id = #{userId}; "
    )
    List<Integer> getAuctionsByParticipant(@Param("userId") int userId);

}
