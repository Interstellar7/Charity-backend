package ru.relex.charity.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.relex.charity.db.mappers.UserSecurityMapper;
import ru.relex.charity.security.model.UserDetailsImpl;

import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserSecurityMapper mapper;

    @Autowired
    public UserDetailsServiceImpl(UserSecurityMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = mapper.findUserByUsername(username);

        if (user == null) {
            return null;
        }

        return new UserDetailsImpl(user);
    }
}
