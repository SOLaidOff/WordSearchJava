package com.solo.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FoundWordTest {
    @Test
    @DisplayName("Verify String output")
    void verifyStringOutput() {
        FoundWord word = new FoundWord("TEST", new GridCoordinate(3, 3), new GridCoordinate(0, 0));
        assertEquals("TEST 3:3 0:0", word.toString());
    }
}
