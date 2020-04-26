package ru.relex.charity.rest.dto;

import ru.relex.charity.services.dto.user.UserDto;

public class ProfileDto {
    private boolean authenticated;
    private UserDto info;

    public ProfileDto(boolean authenticated) {
        this.authenticated = authenticated;
        this.info = null;
    }

    public ProfileDto(boolean authenticated, UserDto info) {
        this.authenticated = authenticated;
        this.info = info;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public UserDto getInfo() {
        return info;
    }

    public void setInfo(UserDto info) {
        this.info = info;
    }
}
