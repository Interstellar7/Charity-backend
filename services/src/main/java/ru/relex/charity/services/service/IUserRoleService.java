package ru.relex.charity.services.service;

import ru.relex.common.models.Role;

import java.util.List;

public interface IUserRoleService {
    List<Role> findRolesByUserId(final int id);

    void assign (final int userId, final Role role);

    void resign (final int userId, final Role role);

    void replace(final int userId, final Role[] roles);
}
