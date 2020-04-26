package ru.relex.charity.services.mapstruct;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import ru.relex.charity.db.mappers.UserMapper;
import ru.relex.charity.db.models.Participant;
import ru.relex.charity.services.dto.AuctionDto.ParticipantDto;
import ru.relex.charity.services.dto.user.UserDto;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ParticipantStruct {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserStruct userStruct;

    @AfterMapping
    protected void setPersonalInfo(Participant participant, @MappingTarget ParticipantDto participantDto) {
        UserDto userDto = userStruct.toDto(userMapper.getUserById(participant.getUserId()));
        participantDto.setPersonalInfoDto(userDto.getPersonalInfo());
    }

    public abstract ParticipantDto toDto(Participant participant);

    public abstract Participant fromDto(ParticipantDto participantDto);

    public abstract List<ParticipantDto> toDto(List<Participant> participants);

    public abstract List<Participant> fromDto(List<ParticipantDto> participantDtos);
}
