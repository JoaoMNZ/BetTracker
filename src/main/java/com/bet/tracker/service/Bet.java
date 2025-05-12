package com.bet.tracker.service;

import java.time.LocalDate;

record Bet(Long id, Long bettorID, Long platformID, String name, Double value, LocalDate date) {
}
