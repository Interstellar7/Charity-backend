package ru.relex.charity.services.mapstruct;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import ru.relex.charity.db.mappers.AuctionMapper;
import ru.relex.charity.db.mappers.ParticipantMapper;
import ru.relex.charity.db.models.Auction;
import ru.relex.charity.db.models.AuctionView;
import ru.relex.charity.db.models.Participant;
import ru.relex.charity.services.dto.AuctionDto.AuctionDto;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class AuctionStruct {

    @Autowired
    private ParticipantMapper participantMapper;
    @Autowired
    private ParticipantStruct participantStruct;
    @Autowired
    private AuctionMapper auctionMapper;

    public abstract AuctionDto toDto(Auction auction);

    public abstract Auction fromDto(AuctionDto auctionDto);

    @AfterMapping
    protected void setParticipants(Auction auction, @MappingTarget AuctionDto auctionDto) {
        // добавляем список участников
        AuctionView auctionView = auctionMapper.getAdditionalInfoAboutAuction(auction.getAuctionId());
        List<Participant> participants = participantMapper.getParticipantsByAuctionId(auction.getAuctionId());
        auctionDto.setParticipants(participantStruct.toDto(participants));
        // добавляем сущность победителя
        if (auction.getWinnerId() > 0) {
            Participant winner = participantMapper.getParticipant(auction.getAuctionId(), auction.getWinnerId());
            auctionDto.setWinner(participantStruct.toDto(winner));
        }
        // добавляем id алвдельца
        auctionDto.setOwnerId(auctionView.getOwnerId());
        // добавляеи title
        auctionDto.setTitle(auctionView.getTitle());
        // добавляем description
        auctionDto.setDescription(auctionView.getDescription());
    }
}
