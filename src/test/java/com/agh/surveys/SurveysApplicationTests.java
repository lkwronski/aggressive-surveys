package com.agh.surveys;

import org.apache.commons.validator.routines.EmailValidator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SpringBootTest
class SurveysApplicationTests {

	@Test
	void contextLoads() {

		 assertFalse(EmailValidator.getInstance().isValid("email"));
	}

}
