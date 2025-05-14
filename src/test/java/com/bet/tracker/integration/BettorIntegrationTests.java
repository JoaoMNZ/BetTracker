package com.bet.tracker.integration;

import com.bet.tracker.domain.Bettor;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BettorIntegrationTests {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void shouldCreateANewBettor() {
        ResponseEntity<Void> createResponse = testRestTemplate.postForEntity("/bettracker",
                new Bettor(null,"Joao","joao","joao123"), Void.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void shouldNotCreateANewBettorThatUsernameAlreadyExists() {
        ResponseEntity<Void> createResponse = testRestTemplate.postForEntity("/bettracker",
                new Bettor(null, "Felipe2", "felipe", "felipe321"), Void.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }

}
