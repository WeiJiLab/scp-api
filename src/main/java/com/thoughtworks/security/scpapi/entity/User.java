package com.thoughtworks.security.scpapi.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class User implements UserDetails  {
    private static final long serialVersionUID = 9111739310824425687L;
    
    private long uid;
    private String username;
    private Collection<? extends GrantedAuthority> authorities;

    public User(long uid, String username, Collection<? extends GrantedAuthority> authorities) {
        this.uid = uid;
        this.username = username;
        this.authorities = authorities;
    }

    public long getUid() {
        return this.uid;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
