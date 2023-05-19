package me.nalab.survey.jpa.adaptor.exception.survey;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SurveyDoesNotExistExceptionTest {

	@Test
	void SURVEY_DOES_NOT_EXIST_EXCEPTION_TEST() {
		Long surveyId = 1L;
		SurveyDoesNotExistException exception = assertThrows(
			SurveyDoesNotExistException.class, () -> {
				throw new SurveyDoesNotExistException(surveyId);
			});

		String expectedMessage = "Cannot find Survey where surveyId \"" + surveyId + "\"";
		String actualMessage = exception.getMessage();
		Assertions.assertEquals(expectedMessage, actualMessage);
	}
}
