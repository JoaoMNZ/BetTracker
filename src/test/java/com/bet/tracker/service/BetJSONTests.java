package com.bet.tracker.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.json.ObjectContent;

import java.io.IOException;
import java.time.LocalDate;

@JsonTest
public class BetJSONTests {
    @Autowired
    JacksonTester<Bet> jacksonTester;

    @Test
    public void BetSerializationTest() throws IOException {
        Bet bet = new Bet(1L,1L,1L,"Barcelona ML",5.00, LocalDate.of(2025,5,12));
        JsonContent<Bet> serializedBet = jacksonTester.write(bet);
        assertThat(serializedBet).isStrictlyEqualToJson("Bet.json");
        assertThat(serializedBet).hasJsonPathNumberValue("@.id");
        assertThat(serializedBet).extractingJsonPathNumberValue("@.id").isEqualTo(1);
        assertThat(serializedBet).hasJsonPathStringValue("@.name");
        assertThat(serializedBet).extractingJsonPathStringValue("@.name").isEqualTo("Bet365");
    }
}
