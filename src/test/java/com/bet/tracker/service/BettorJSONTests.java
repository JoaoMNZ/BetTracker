package com.bet.tracker.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.json.ObjectContent;

import java.io.IOException;

@JsonTest
public class BettorJSONTests {

    @Autowired
    JacksonTester<Bettor> jacksonTester;

    @Test
    public void bettorSerializationTest() throws IOException {
        Bettor bettor = new Bettor(1L,"João");
        JsonContent<Bettor> serializedBettor = jacksonTester.write(bettor);
        assertThat(serializedBettor).isStrictlyEqualToJson("Bettor.json");
        assertThat(serializedBettor).hasJsonPathNumberValue("@.id");
        assertThat(serializedBettor).extractingJsonPathNumberValue("@.id").isEqualTo(1);
        assertThat(serializedBettor).hasJsonPathStringValue("@.name");
        assertThat(serializedBettor).extractingJsonPathStringValue("@.name").isEqualTo("João");
    }

    @Test
    public void bettorDeserializationTest() throws IOException {
        Bettor bettor = new Bettor(1L, "João");
        String expected = """
                {
                    "id": 1,
                    "name": "João"
                }
                """;
        ObjectContent<Bettor> deserializedBettor = jacksonTester.parse(expected);
        assertThat(deserializedBettor).isEqualTo(bettor);
        assertThat(deserializedBettor.getObject().id()).isEqualTo(bettor.id());
        assertThat(deserializedBettor.getObject().name()).isEqualTo(bettor.name());
    }
}
