package com.bet.tracker.repository;

import com.bet.tracker.domain.Bettor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BettorRepository extends CrudRepository<Bettor, Long> {
    Optional<Bettor> findByUsername(String username);
    Boolean existsByUsername(String username);
}
