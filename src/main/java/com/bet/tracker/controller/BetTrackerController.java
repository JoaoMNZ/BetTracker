package com.bet.tracker.controller;

import com.bet.tracker.domain.Bettor;
import com.bet.tracker.repository.BettorRepository;
import com.bet.tracker.service.BettorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bettracker")
public class BetTrackerController {

    @Autowired
    private BettorService bettorService;

    @PostMapping
    public ResponseEntity<Void> createBettor(@RequestBody Bettor bettor){
        return bettorService.createBettor(bettor)
                ? ResponseEntity.status(HttpStatus.CREATED).build()
                : ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody Bettor bettor) {
        try {
            String token = bettorService.authenticateBettor(bettor);
            return ResponseEntity.ok(token);
        } catch (UsernameNotFoundException | BadCredentialsException error) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
