package com.agh.surveys.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CommonValidator {

    public boolean isBlank(String string){
        return StringUtils.isBlank(string);
    }

    public boolean areBlank(String ... strings){
        return StringUtils.isAllBlank(strings);
    }
}
