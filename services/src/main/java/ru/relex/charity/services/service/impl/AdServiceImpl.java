package ru.relex.charity.services.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.relex.charity.db.mappers.AdMapper;
import ru.relex.charity.services.dto.ad.AdDto;
import ru.relex.charity.services.mapstruct.AdStruct;
import ru.relex.charity.services.service.IAdService;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public class AdServiceImpl implements IAdService {
    private final AdMapper adMapper;
    private final AdStruct adStruct;

    @Autowired
    public AdServiceImpl(AdMapper adMapper, AdStruct adStruct) {
        this.adMapper = adMapper;
        this.adStruct = adStruct;
    }


    @Override
    public List<AdDto> getAds(String search) {
        var ads = adMapper.getAds(search);

        return adStruct.toDto(ads);
    }

    @Override
    public AdDto getById(int adId) {
        var ad = adMapper.getAdById(adId);

        return adStruct.toDto(ad);
    }

    @Override
    public AdDto create(@Valid AdDto adDto) {
        var ad = adStruct.fromDto(adDto);

        adMapper.insert(ad);

        return adStruct.toDto(ad);
    }

    @Override
    public AdDto update(@Valid AdDto adDto) {
        var ad = adStruct.fromDto(adDto);

        adMapper.update(ad);

        return adStruct.toDto(ad);
    }

    @Override
    public void remove(int adId) {
        adMapper.delete(adId);
    }
}
