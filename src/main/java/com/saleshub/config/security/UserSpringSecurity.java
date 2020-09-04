package com.saleshub.config.security;

import com.saleshub.domain.enums.CustomerProfile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public class UserSpringSecurity implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String email;
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserSpringSecurity(){}

    public UserSpringSecurity(Integer id, String email, String password,
                              Set<CustomerProfile> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = CustomerProfile.toGrantedAuthorities(authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Integer getId(){
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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

    public boolean hasRole(CustomerProfile profile) {

        return getAuthorities()
                .contains(new SimpleGrantedAuthority(profile.getRole()));
    }
}
