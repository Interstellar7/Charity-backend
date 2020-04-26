package ru.relex.charity.services.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.relex.charity.db.mappers.UserMapper;
import ru.relex.charity.db.mappers.UserRoleMapper;
import ru.relex.charity.db.models.User;
import ru.relex.charity.services.constraint.ConstraintMessage;
import ru.relex.charity.services.dto.user.UserDto;
import ru.relex.charity.services.dto.user.UserUpdateDto;
import ru.relex.charity.services.mapstruct.CurrentUserStruct;
import ru.relex.charity.services.mapstruct.UserStruct;
import ru.relex.charity.services.service.IPasswordEncoderService;
import ru.relex.charity.services.service.IUserService;
import ru.relex.charity.services.exceptions.BadRequestException;
import ru.relex.common.models.CurrentUser;
import ru.relex.common.models.Role;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
public class UserServiceImpl implements IUserService {
    private final UserMapper userMapper;
    private final UserStruct userStruct;
    private final UserRoleMapper userRoleMapper;
    private final IPasswordEncoderService passwordEncoderService;
    private final CurrentUser currentUser;
    private final AuthenticationManager authenticationManager;
    private final CurrentUserStruct currentUserStruct;


    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserStruct userStruct, UserRoleMapper userRoleMapper, IPasswordEncoderService passwordEncoderService, CurrentUser currentUser, AuthenticationManager authenticationManager, CurrentUserStruct currentUserStruct) {
        this.userMapper = userMapper;
        this.userStruct = userStruct;
        this.userRoleMapper = userRoleMapper;
        this.passwordEncoderService = passwordEncoderService;
        this.currentUser = currentUser;
        this.authenticationManager = authenticationManager;
        this.currentUserStruct = currentUserStruct;
    }


    @Override
    public List<UserDto> findUsers(String search) {
        var users = userMapper.getUsers(search);

        return userStruct.toDto(users);
    }

    @Override
    public UserDto getById(int userId) {
        var user = userMapper.getUserById(userId);

        return userStruct.toDto(user);
    }

    @Override
    public UserDto create(@Valid UserDto userDto) {
        var user = createUser(userDto);

        return userStruct.toDto(user);
    }

    @Override
    public UserDto update(@Valid UserUpdateDto userDto) {
        var user = userMapper.getUserById(userDto.getId());
        var pi = userDto.getPersonalInfo();

        user.setFirstName(pi.getFirstName());
        user.setLastName(pi.getLastName());
        user.setEmail(pi.getEmail());

        userMapper.update(user);

        return userStruct.toDto(user);
    }

    @Override
    public void remove(int userId) {
        userMapper.delete(userId);
    }

    @Override
    public UserDto createWithLogin(@Valid UserDto userDto) {
        var user = createUser(userDto);

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toList());

        CurrentUser curUser = currentUserStruct.toDto(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(curUser, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return userStruct.toDto(user);
    }

    private User createUser(UserDto userDto) {
        userDto.setRoles(Collections.singletonList(Role.USER));

        if (existUsername(userDto.getUsername())) {
            throw new BadRequestException(ConstraintMessage.Field.USERNAME + ConstraintMessage.Constraint.DUPLICATE);
        }

        if (existEmail(userDto.getPersonalInfo().getEmail())) {
            throw new BadRequestException(ConstraintMessage.Field.EMAIL + ConstraintMessage.Constraint.DUPLICATE);
        }
        var user = userStruct.fromDto(userDto);

        user.setPassword(passwordEncoderService.encode(user.getPassword()));

        userMapper.insert(user);

        // назначение дефолтных ролей
        for (var role : user.getRoles()) {
            userRoleMapper.assign(user.getId(), role.getId());
        }

        return user;
    }

    @Override
    public boolean existUsername(String username) {
        Optional<Boolean> exist = userMapper.existUsername(username);

        return exist.isPresent();
    }

    @Override
    public boolean existEmail(String email) {
        Optional<Boolean> exist = userMapper.existEmail(email);

        return exist.isPresent();
    }

    @Override
    public boolean existEmailExceptUser(String email, Integer id) {
        Optional<Boolean> exist = userMapper.existEmailExceptUser(email, id);

        return exist.isPresent();
    }
}
