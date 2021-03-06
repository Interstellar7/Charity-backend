package ru.relex.charity.security.model;

import ru.relex.common.models.CurrentUser;
import ru.relex.common.models.Role;

import java.util.List;

public class LoginSuccessModel implements CurrentUser {
    private int id;
    private String username;
    private List<Role> roles;

    public LoginSuccessModel(CurrentUser info) {
        this.id = info.getId();
        this.username = info.getUsername();
        this.roles = info.getRoles();
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
