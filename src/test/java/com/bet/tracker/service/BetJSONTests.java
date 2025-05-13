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
        assertThat(serializedBet).hasJsonPathNumberValue("@.bettorID");
        assertThat(serializedBet).extractingJsonPathNumberValue("@.bettorID").isEqualTo(1);
        assertThat(serializedBet).hasJsonPathNumberValue("@.platformID");
        assertThat(serializedBet).extractingJsonPathNumberValue("@.platformID").isEqualTo(1);
        assertThat(serializedBet).hasJsonPathStringValue("@.name");
        assertThat(serializedBet).extractingJsonPathStringValue("@.name").isEqualTo("Barcelona ML");
        assertThat(serializedBet).hasJsonPathNumberValue("@.value");
        assertThat(serializedBet).extractingJsonPathNumberValue("@.value").isEqualTo(5.00);
        assertThat(serializedBet).hasJsonPathStringValue("@.date");
        assertThat(serializedBet).extractingJsonPathStringValue("@.date").isEqualTo("2025-05-12");
    }

    @Test
    public void BetDeserializationTest() throws IOException{
        Bet bet = new Bet(1L,1L,1L,"Barcelona ML",5.00, LocalDate.of(2025,5,12));
        String expected = """
                {
                  "id": 1,
                  "bettorID": 1,
                  "platformID": 1,
                  "name": "Barcelona ML",
                  "value": 5.00,
                  "date": "2025-05-12"
                }
                """;
        ObjectContent<Bet> deserializedBet = jacksonTester.parse(expected);
        assertThat(deserializedBet).isEqualTo(bet);
        assertThat(deserializedBet.getObject().id()).isEqualTo(bet.id());
        assertThat(deserializedBet.getObject().bettorID()).isEqualTo(bet.bettorID());
        assertThat(deserializedBet.getObject().platformID()).isEqualTo(bet.platformID());
        assertThat(deserializedBet.getObject().name()).isEqualTo(bet.name());
        assertThat(deserializedBet.getObject().value()).isEqualTo(bet.value());
        assertThat(deserializedBet.getObject().date()).isEqualTo(bet.date());

    }
}
