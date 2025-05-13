package com.bet.tracker.repository;

import com.bet.tracker.domain.Bettor;
import org.springframework.data.repository.CrudRepository;

public interface BettorRepository extends CrudRepository<Bettor, Long> {
    Boolean existsByUsername(String username);
}
