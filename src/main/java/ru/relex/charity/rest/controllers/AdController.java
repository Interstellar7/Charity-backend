package ru.relex.charity.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.relex.charity.services.service.IAdService;
import ru.relex.charity.services.dto.ad.AdDto;

import java.util.List;

@RestController
@RequestMapping(
        path = "/api/ads",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class AdController {
    private final IAdService adService;

    @Autowired
    public AdController(IAdService adService) {
        this.adService = adService;
    }

    @GetMapping
    List<AdDto> getAds(@RequestParam(name = "search", required = false) String search) {
        return adService.getAds(search);
    }

    @GetMapping("/{id}")
    AdDto findById(@PathVariable("id") int id) {
        return adService.getById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    AdDto create(@RequestBody AdDto adDto) {
        adService.create(adDto);
        return adDto;
    }

    @PutMapping("/{id}")
    AdDto update(@PathVariable("id") int id, @RequestBody AdDto adDto) {
        adDto.setId(id);
        return adService.update(adDto);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") int id) {
        adService.remove(id);
    }
}
