package ru.relex.charity.services.mapstruct;

import org.mapstruct.Mapper;
import ru.relex.charity.db.models.User;
import ru.relex.charity.services.dto.user.CurrentUserDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CurrentUserStruct {

    CurrentUserDto toDto(User user);

    User fromDto(CurrentUserDto userDto);

    List<CurrentUserDto> toDto(List<User> users);

    List<User> fromDto(List<CurrentUserDto> userDtos);
}
