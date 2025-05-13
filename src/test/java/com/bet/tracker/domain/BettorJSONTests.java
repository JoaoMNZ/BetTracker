package com.bet.tracker.domain;

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
        Bettor bettor = new Bettor(1L, "Felipe","felipe","felipe123");
        JsonContent<Bettor> serializedBettor = jacksonTester.write(bettor);
        assertThat(serializedBettor).isStrictlyEqualToJson("Bettor.json");
        assertThat(serializedBettor).hasJsonPathNumberValue("@.id");
        assertThat(serializedBettor).extractingJsonPathNumberValue("@.id").isEqualTo(1);
        assertThat(serializedBettor).hasJsonPathStringValue("@.name");
        assertThat(serializedBettor).extractingJsonPathStringValue("@.name").isEqualTo("Felipe");
        assertThat(serializedBettor).hasJsonPathStringValue("@.username");
        assertThat(serializedBettor).extractingJsonPathStringValue("@.username").isEqualTo("felipe");
        assertThat(serializedBettor).hasJsonPathStringValue("@.password");
        assertThat(serializedBettor).extractingJsonPathStringValue("@.password").isEqualTo("felipe123");
    }

    @Test
    public void bettorDeserializationTest() throws IOException {
        Bettor bettor = new Bettor(1L, "Felipe","felipe","felipe123");
        String expected = """
                {
                    "id": 1,
                    "name": "Felipe",
                    "username": "felipe",
                    "password": "felipe123"
                }
                """;
        ObjectContent<Bettor> deserializedBettor = jacksonTester.parse(expected);
        assertThat(deserializedBettor).isEqualTo(bettor);
        assertThat(deserializedBettor.getObject().getId()).isEqualTo(bettor.getId());
        assertThat(deserializedBettor.getObject().getName()).isEqualTo(bettor.getName());
        assertThat(deserializedBettor.getObject().getUsername()).isEqualTo(bettor.getUsername());
        assertThat(deserializedBettor.getObject().getPassword()).isEqualTo(bettor.getPassword());
    }
}
