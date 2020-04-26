package ru.relex.charity.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.relex.charity.db.models.SecurityUserDetails;
import ru.relex.common.models.CurrentUser;
import ru.relex.common.models.Role;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails, CurrentUser {
    private final SecurityUserDetails user;

    public UserDetailsImpl(SecurityUserDetails user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles()
                .stream()
                .map(userRole -> new SimpleGrantedAuthority("ROLE_" + userRole.name()))
                .collect(Collectors.toSet());
    }

    @Override
    public int getId() {
        return user.getId();
    }

    @Override
    public String getPassword() {
        return user.getPassword().strip(); // strip
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public List<Role> getRoles() {
        return user.getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
