package com.agh.surveys.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CommonValidator {

    public boolean isBlank(String string){
        return StringUtils.isBlank(string);
    }

    public boolean areBlank(String ... strings){
        return StringUtils.isAllBlank(strings);
    }

    public LocalDateTime createdPollDeadlineMargin(int minutes) {

        return LocalDateTime.now().plusMinutes(minutes);
    }
}
