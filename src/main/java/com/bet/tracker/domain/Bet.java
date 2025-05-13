package com.bet.tracker.domain;

import java.time.LocalDate;

public record Bet(Long id, Long bettorID, Long platformID, String name, Double value, LocalDate date) {
}
