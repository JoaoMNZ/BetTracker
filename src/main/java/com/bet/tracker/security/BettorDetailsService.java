package com.bet.tracker.security;

import com.bet.tracker.domain.Bettor;
import com.bet.tracker.repository.BettorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BettorDetailsService implements UserDetailsService {

    @Autowired
    private BettorRepository bettorRepository;

    @Override
    public BettorDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Bettor bettor = bettorRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Bettor not found."));
        return new BettorDetails(bettor);
    }
}
