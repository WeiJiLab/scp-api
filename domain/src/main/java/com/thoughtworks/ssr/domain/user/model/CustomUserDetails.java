package com.thoughtworks.ssr.domain.user.model;

import com.thoughtworks.ssr.domain.role.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.thoughtworks.ssr.domain.role.model.ERole.ROLE_ADMIN;


public class CustomUserDetails extends User implements UserDetails {

    private static final long serialVersionUID = 5177998329242287838L;

    public CustomUserDetails(final User user) {
        super(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<Role> roles = getRoles();

        if (ObjectUtils.isEmpty(roles)) {
            return new ArrayList<>();
        }
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return super.getActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return super.getActive() && super.getIsEmailVerified();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        var that = (CustomUserDetails) obj;
        return Objects.equals(getId(), that.getId());
    }

    public boolean isAdmin() {
        return getRoles()
                .stream()
                .anyMatch(it -> it.getRole().equals(ROLE_ADMIN));
    }
}
