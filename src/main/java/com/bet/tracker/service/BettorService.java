package com.bet.tracker.service;

import com.bet.tracker.domain.Bettor;
import com.bet.tracker.repository.BettorRepository;
import com.bet.tracker.security.BettorDetails;
import com.bet.tracker.security.JwtTokenService;
import com.bet.tracker.security.SecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class BettorService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private BettorRepository bettorRepository;
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private SecurityConfiguration securityConfiguration;

    public Boolean createBettor(Bettor bettor){
        if(!bettorRepository.existsByUsername(bettor.getUsername())){
            bettorRepository.save(new Bettor(null,
                    bettor.getName(),
                    bettor.getUsername(),
                    securityConfiguration.passwordEncoder().encode(bettor.getPassword())));
            return true;
        }
        return false;
    }

    public String authenticateBettor(Bettor bettor) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(bettor.getUsername(), bettor.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        BettorDetails bettorDetails = (BettorDetails) authentication.getPrincipal();
        return jwtTokenService.generateToken(bettorDetails);
    }
}
