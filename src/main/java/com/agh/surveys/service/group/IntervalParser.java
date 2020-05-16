package com.agh.surveys.service.group;

import com.agh.surveys.exception.BadRequestException;
import org.springframework.stereotype.Component;
import sun.security.pkcs.ParsingException;

import java.text.ParseException;
import java.time.Duration;
import java.util.Arrays;

@Component
public class IntervalParser {

    private static String intervalPattern = "[0-9]+d[0-9][0-9]?h";
    private static String dayMark="d";
    private static String hourMark="h";

    public Duration fromString(String input){
        if (!input.matches(intervalPattern)){
            throw new BadRequestException("Interval must be a {daysNumber}d{hoursNumber}h - for example 3d6h.\n " +
                    "If hours or days must be zero then write 0d6h or 3d0h ");
        }
        int[] daysAndHours = parse(input.split(dayMark));//splits regex into array, for example 3d6h into [3,6h]

        return Duration.ofDays(daysAndHours[0]).plusHours(daysAndHours[1]);
    }

    private int[] parse(String[] input){
        int hours= 0;
        int days = 0;
        try{
            days = Integer.parseInt(input[0]);
            hours = Integer.parseInt(input[1].split(hourMark)[0]); // split is used to remove 'h' from second string
        }catch(Exception ex){
            throw new BadRequestException("Cannot parse string - unknown reason! String after split: " + Arrays.deepToString(input) );
        }
        return new int[]{days,hours};
    }
}
