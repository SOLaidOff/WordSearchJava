package com.solo.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GridCoordinateTest {
    @Test
    @DisplayName("Verify coordinate input")
    void verifyCoordinateInput() {
        GridCoordinate coordinate = new GridCoordinate(4, 2);
        assertEquals(4, coordinate.getRow(), "Coordinates save row value");
        assertEquals(2, coordinate.getCol(), "Coordinates save col value");
    }

    @Test
    @DisplayName("Verify String output")
    void verifyStringOutput() {
        GridCoordinate coordinate = new GridCoordinate(4, 2);
        assertEquals("4:2", coordinate.toString(), "Coordinates display in 'row:col: format");
    }
}
