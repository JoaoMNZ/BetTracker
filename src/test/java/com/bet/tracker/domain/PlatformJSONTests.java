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
public class PlatformJSONTests {
    @Autowired
    JacksonTester<Platform> jacksonTester;

    @Test
    public void platformSerializationTest() throws IOException {
        Platform platform = new Platform(1L,"Bet365");
        JsonContent<Platform> serializedPlatform = jacksonTester.write(platform);
        assertThat(serializedPlatform).isStrictlyEqualToJson("Platform.json");
        assertThat(serializedPlatform).hasJsonPathNumberValue("@.id");
        assertThat(serializedPlatform).extractingJsonPathNumberValue("@.id").isEqualTo(1);
        assertThat(serializedPlatform).hasJsonPathStringValue("@.name");
        assertThat(serializedPlatform).extractingJsonPathStringValue("@.name").isEqualTo("Bet365");
    }

    @Test
    public void platformDeserializationTest() throws IOException{
        Platform platform = new Platform(1L, "Bet365");
        String expected = """
                {
                    "id": 1,
                    "name": "Bet365"
                }
                """;
        ObjectContent<Platform> deserializedPlatform = jacksonTester.parse(expected);
        assertThat(deserializedPlatform).isEqualTo(platform);
        assertThat(deserializedPlatform.getObject().id()).isEqualTo(1);
        assertThat(deserializedPlatform.getObject().name()).isEqualTo("Bet365");
    }
}
