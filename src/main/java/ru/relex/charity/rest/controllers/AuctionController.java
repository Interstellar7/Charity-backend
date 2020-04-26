package ru.relex.charity.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.relex.charity.db.mappers.AuctionMapper;
import ru.relex.charity.db.models.Participant;
import ru.relex.charity.services.dto.AuctionDto.AuctionDto;
import ru.relex.charity.services.dto.AuctionDto.ParticipantDto;
import ru.relex.charity.services.service.IAuctionService;

import java.util.List;

@RestController
@RequestMapping(
        path = "/auction",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class AuctionController {
    private final IAuctionService auctionService;

    @Autowired
    public AuctionController(IAuctionService auctionService) {
        this.auctionService = auctionService;
    }

    // Метод createAuction не будет использоваться, поскольку аукцион создаётся не по API, а при создании объявления
    @PostMapping(path = "/create")
    AuctionDto createAuction(@RequestBody AuctionDto auctionDto) {
        return auctionService.createAuction(auctionDto.getAdId());
    }

    @GetMapping(path = "/{auctionId}/get")
    AuctionDto findAuctionById(@PathVariable("auctionId") int auctionId) {
        return auctionService.getAuctionById(auctionId);
    }

    @GetMapping(path = "/get-auctions-by-owner/{userId}")
    List<AuctionDto> findAuctionsByOwner(@PathVariable("userId") int userId) {
        return auctionService.getAuctionsByOwner(userId);
    }

    @GetMapping(path = "/get-auctions-by-participant/{userId}")
    List<AuctionDto> findAuctionsByParticipant(@PathVariable("userId") int userId) {
        return auctionService.getAuctionsByParticipant(userId);
    }

    @PutMapping(path = "/{auctionId}/set-winner/{userId}")
    void setWinner(@PathVariable("auctionId") int auctionId, @PathVariable("userId") int userId) {
        auctionService.setWinner(auctionId, userId);
    }

    @PutMapping(path = "/{auctionId}/close")
    void closeAuction(@PathVariable("auctionId") int auctionId) {
        auctionService.closeAuction(auctionId);
    }

    @GetMapping("/{auctionId}/participants")
    List<ParticipantDto> findParticipantsByAuctionId(@PathVariable("auctionId") int auctionId) {
        return auctionService.getParticipantsByAuctionId(auctionId);
    }

    @GetMapping("/{auctionId}/participants/{userId}")
    ParticipantDto findParticipant(@PathVariable("auctionId") int auctionId, @PathVariable("userId") int userId) {
        return auctionService.getParticipant(auctionId, userId);
    }

    @PostMapping(
            path = "/{auctionId}/add-participant",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    void addParticipant(@RequestBody ParticipantDto participantDto) {
        auctionService.createParticipant(participantDto);
    }

    @DeleteMapping("/{auctionId}/delete-participant/{userId}")
    void deleteParticipant(@PathVariable("auctionId") int auctionId, @PathVariable("userId") int userId) {
        auctionService.deleteParticipant(auctionId, userId);
    }
}
