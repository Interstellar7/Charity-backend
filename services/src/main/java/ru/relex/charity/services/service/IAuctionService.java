package ru.relex.charity.services.service;

import ru.relex.charity.services.dto.AuctionDto.AuctionDto;
import ru.relex.charity.services.dto.AuctionDto.ParticipantDto;

import java.util.List;

public interface IAuctionService {

    AuctionDto createAuction(int adId);

    AuctionDto getAuctionById(int auctionId);

    List<AuctionDto> getAuctionsByOwner(int userId);

    List<AuctionDto> getAuctionsByParticipant(int userId);

    void setWinner(int auctionId, int winnerId);

    void closeAuction(int auctionId);

    List<ParticipantDto> getParticipantsByAuctionId(int auctionId);

    ParticipantDto getParticipant(int auctionId, int userId);

    ParticipantDto createParticipant(ParticipantDto participantDto);

    void deleteParticipant(int auctionId, int userId);
}
