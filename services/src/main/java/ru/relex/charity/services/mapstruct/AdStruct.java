package ru.relex.charity.services.mapstruct;

import org.mapstruct.Mapper;
import ru.relex.charity.db.models.Ad;
import ru.relex.charity.services.dto.ad.AdDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdStruct {
    AdDto toDto(Ad ad);

    Ad fromDto(AdDto AdDto);

    List<AdDto> toDto(List<Ad> ads);

    List<Ad> fromDto(List<AdDto> AdDto);
}
