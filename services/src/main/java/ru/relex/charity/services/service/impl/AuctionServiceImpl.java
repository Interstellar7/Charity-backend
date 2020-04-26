package ru.relex.charity.services.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.relex.charity.db.mappers.AuctionMapper;
import ru.relex.charity.db.mappers.ParticipantMapper;
import ru.relex.charity.db.models.Auction;
import ru.relex.charity.db.models.Participant;
import ru.relex.charity.services.dto.AuctionDto.AuctionDto;
import ru.relex.charity.services.dto.AuctionDto.ParticipantDto;
import ru.relex.charity.services.mapstruct.AuctionStruct;
import ru.relex.charity.services.mapstruct.ParticipantStruct;
import ru.relex.charity.services.service.IAuctionService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuctionServiceImpl implements IAuctionService {
    private final AuctionMapper auctionMapper;
    private final ParticipantMapper participantMapper;
    private final ParticipantStruct participantStruct;
    private final AuctionStruct auctionStruct;

    @Autowired
    public AuctionServiceImpl(AuctionMapper auctionMapper, ParticipantMapper participantMapper, ParticipantStruct participantStruct, AuctionStruct auctionStruct) {
        this.auctionMapper = auctionMapper;
        this.participantMapper = participantMapper;
        this.participantStruct = participantStruct;
        this.auctionStruct = auctionStruct;
    }

    @Override
    public AuctionDto getAuctionById(int auctionId) {
        Auction auction = auctionMapper.findAuctionById(auctionId);
        return auctionStruct.toDto(auction);
    }

    @Override
    public List<AuctionDto> getAuctionsByOwner(int userId) {
        List<Auction> auctionList = auctionMapper.findAuctionsByOwner(userId);
        List<AuctionDto> auctionDtoList = new ArrayList<>();
        for (Auction auction : auctionList) {
            auctionDtoList.add(auctionStruct.toDto(auction));
        }
        return auctionDtoList;
    }

    @Override
    public List<AuctionDto> getAuctionsByParticipant(int userId) {
        List<Integer> idList = participantMapper.getAuctionsByParticipant(userId);
        List<AuctionDto> auctionDtoList = new ArrayList<>();
        if (idList.size() > 0) {
            for (Integer auctionId : idList) {
                auctionDtoList.add(auctionStruct.toDto(auctionMapper.findAuctionById(auctionId)));
            }
        }
        return auctionDtoList;
    }

    @Override
    public AuctionDto createAuction(int adId) {
        auctionMapper.createAuction(adId);
        int lastId = auctionMapper.findLastAuctionId(adId);
        Auction auction = auctionMapper.findAuctionById(lastId);
        return auctionStruct.toDto(auction);
    }

    @Override
    public void setWinner(int auctionId, int winnerId) {
        auctionMapper.setWinner(auctionId, winnerId);
    }

    @Override
    public void closeAuction(int auctionId) {
        auctionMapper.closeAuction(auctionId);
    }

    @Override
    public List<ParticipantDto> getParticipantsByAuctionId(int auctionId) {
        List<Participant> participants = participantMapper.getParticipantsByAuctionId(auctionId);
        return participantStruct.toDto(participants);
    }

    @Override
    public ParticipantDto getParticipant(int auctionId, int userId) {
        return participantStruct.toDto(participantMapper.getParticipant(auctionId, userId));
    }

    @Override
    public ParticipantDto createParticipant(ParticipantDto participantDto) {
        Participant participant = participantStruct.fromDto(participantDto);
        participantMapper.insertParticipant(
                participant.getAuctionId(),
                participant.getUserId(),
                participant.getMessageToGiver() );
        return participantDto;
    }

    @Override
    public void deleteParticipant(int auctionId, int userId) {
        participantMapper.deleteParticipant(auctionId, userId);
    }
}
