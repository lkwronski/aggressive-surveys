package com.agh.surveys.service.group;

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
        assertEquals(output,excpected);


    }
}