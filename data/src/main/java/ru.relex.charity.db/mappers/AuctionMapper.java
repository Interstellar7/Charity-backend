package ru.relex.charity.db.mappers;

import org.apache.ibatis.annotations.*;
import ru.relex.charity.db.models.Auction;
import ru.relex.charity.db.models.AuctionView;
import ru.relex.charity.db.models.Participant;

import java.util.List;

@Mapper
public interface AuctionMapper {

    @Select(
            // language=PostgreSQL
            "SELECT " +
                    "auction_id, " +
                    "ad_id, " +
                    "winner_id, " +
                    "opened_at, " +
                    "closed_at, " +
                    "is_alive " +
            "FROM auctions " +
            "WHERE auction_id = #{auctionId};"
    )
    Auction findAuctionById(@Param("auctionId") int auctionId);

    @Insert(
            // language=PostgreSQL
            "INSERT INTO auctions " +
                    "(ad_id, opened_at, is_alive) " +
                    "VALUES (#{adId}, CURRENT_TIMESTAMP, true); "
    )
    void createAuction(@Param("adId") int adId);

    @Select(
            // language=PostgreSQL
            "SELECT max(auction_id) as maxAuctionId " +
                    "FROM auctions " +
                    "WHERE ad_id = #{adId};"
    )
    int findLastAuctionId(@Param("adId") int adId);

    @Select(
            "SELECT " +
                    "auctions.auction_id, " +
                    "ads.created_by as owner_id, " +
                    "ads.title, " +
                    "ads.description " +
            "FROM auctions " +
            "JOIN ads ON (ads.ad_id = auctions.ad_id) " +
            "WHERE auction_id = #{auctionId};"
    )
    AuctionView getAdditionalInfoAboutAuction(@Param("auctionId") int auctionId);

    @Select(
            // language=PostgreSQL
            "SELECT " +
                "auctions.auction_id, " +
                "auctions.ad_id, " +
                "auctions.winner_id, " +
                "auctions.opened_at, " +
                "auctions.closed_at, " +
                "auctions.is_alive, " +
                "ads.created_by as owner_id " +
            "FROM auctions " +
            "JOIN ads ON (ads.ad_id = auctions.ad_id) " +
            "WHERE ads.created_by = #{userId};"
    )
    List<Auction> findAuctionsByOwner(@Param("userId") int userId);

    //List<Auction> findAuctionsByParticipant(@Param("userId") int user)


    @Update(
            // language=PostgreSQL
            "UPDATE auctions " +
            "SET winner_id = #{winnerId} " +
            "WHERE auction_id = #{auctionId}"
    )
    void setWinner(@Param("auctionId") int auctionId, @Param("winnerId") int winnerId);

    @Update(
            // language=PostgreSQL
            "UPDATE auctions SET " +
                    "is_alive = false, " +
                    "closed_at = CURRENT_TIMESTAMP " +
            "WHERE auction_id = #{auctionId};"
    )
    void closeAuction(@Param("auctionId") int auctionId);

}

