package ru.relex.charity.services.service;

import ru.relex.charity.services.dto.ad.AdDto;

import javax.validation.Valid;
import java.util.List;

public interface IAdService {
    List<AdDto> getAds(String search);

    AdDto getById(int adId);

    AdDto create(@Valid AdDto adDto);

    AdDto update(@Valid AdDto adDto);

    void remove(int adId);
}
