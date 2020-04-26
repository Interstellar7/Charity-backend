package ru.relex.charity.services.service;

import ru.relex.charity.services.dto.user.UserDto;
import ru.relex.charity.services.dto.user.UserUpdateDto;

import javax.validation.Valid;
import java.util.List;

public interface IUserService {
    List<UserDto> findUsers(String search);

    UserDto getById(int userId);

    UserDto create(@Valid UserDto userDto);

    UserDto createWithLogin(@Valid UserDto userDto);

    UserDto update(@Valid UserUpdateDto userDto);

    void remove(int userId);

    boolean existUsername(String username);

    boolean existEmail(String email);
    boolean existEmailExceptUser(String email, Integer id);
}
