package com.bet.tracker.security;

import com.bet.tracker.domain.Bettor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class BettorDetails implements UserDetails {

    private final Bettor bettor;

    public BettorDetails(Bettor bettor){
        this.bettor = bettor;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return bettor.getPassword();
    }

    @Override
    public String getUsername() {
        return bettor.getUsername();
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
