package com.bet.tracker.controller;

import com.bet.tracker.domain.Bettor;
import com.bet.tracker.repository.BettorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bet")
public class BettorController {

    private final BettorRepository bettorRepository;
    private BettorController(BettorRepository bettorRepository){
        this.bettorRepository = bettorRepository;
    }

    @PostMapping
    public ResponseEntity<Void> createBettor(@RequestBody Bettor newBettorRequest){
        Boolean bettorAlreadyExists = bettorRepository.existsByUsername(newBettorRequest.getUsername());
        if(bettorAlreadyExists){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        bettorRepository.save(new Bettor(null, newBettorRequest.getName(), newBettorRequest.getUsername(), newBettorRequest.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
