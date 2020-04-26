package ru.relex.charity.services.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.relex.charity.db.mappers.UserRoleMapper;
import ru.relex.charity.services.service.IUserRoleService;
import ru.relex.common.models.Role;

import java.util.List;

@Service
public class UserRoleServiceImpl implements IUserRoleService {
    private UserRoleMapper userRoleMapper;

    @Autowired
    public UserRoleServiceImpl(UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public List<Role> findRolesByUserId(int id) {
        return userRoleMapper.findRolesByUser(id);
    }

    @Override
    public void assign(int userId, Role role) {
        var roles = findRolesByUserId(userId);
        if (!roles.contains(role)) {
            userRoleMapper.assign(userId, role.getId());
        }
    }

    @Override
    public void resign(int userId, Role role) {
        var roles = findRolesByUserId(userId);
        if (roles.contains(role)) {
            userRoleMapper.resign(userId, role.getId());
        }
    }

    @Override
    public void replace(int userId, Role[] roles) {
        var oldRoles = findRolesByUserId(userId);
        for (Role role: oldRoles) {
            userRoleMapper.resign(userId, role.getId());
        }

        for (Role role: roles) {
            userRoleMapper.assign(userId, role.getId()
            );
        }
    }
}
