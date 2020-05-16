package com.agh.surveys.service.group;

import io.swagger.models.auth.In;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class IntervalParserTest {

    @Test
    void fromString() {
        IntervalParser intervalParser = new IntervalParser();
        String input = "3d6h";
        Duration output = intervalParser.fromString(input);
        Duration excpected = Duration.ofDays(3).plusHours(6);
        assertEquals(output, excpected);
    }

    @Test
    void fromDurationToString() {
        IntervalParser intervalParser = new IntervalParser();
        Duration input = Duration.ofDays(24).plusHours(5);
        String output = intervalParser.fromDurationToString(input);
        String expected = "24d5h";
        assertEquals(output,expected);
    }
}