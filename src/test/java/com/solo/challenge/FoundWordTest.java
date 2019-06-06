package com.solo.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FoundWordTest {
    @Test
    @DisplayName("Verify simple String output")
    void verifySimpleStringOutput() {
        FoundWord word = new FoundWord("TEST", "TEST", new GridCoordinate(3, 3), new GridCoordinate(0, 0));
        assertEquals("TEST 3:3 0:0", word.toString());
    }

    @Test
    @DisplayName("Verify String output with spaces")
    void verifyStringOutputWithSpaces() {
        FoundWord word = new FoundWord("HORS DOEUVRE", "HORSDOEUVRE", new GridCoordinate(1, 5), new GridCoordinate(1, 15));
        assertEquals("HORS DOEUVRE 1:5 1:15", word.toString());
    }
}
