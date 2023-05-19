package me.nalab.survey.jpa.adaptor.exception.target;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TargetDoesNotExistExceptionTest {

	@Test
	void TARGET_DOES_NOT_EXIST_EXCEPTION_TEST() {
		Long targetId = 1L;
		TargetDoesNotExistException exception = assertThrows(
			TargetDoesNotExistException.class, () -> {
				throw new TargetDoesNotExistException(targetId);
			}
		);

		String expectedMessage = "Cannot find Target where targetId \"" + targetId + "\"";
		String actualMessage = exception.getMessage();
		Assertions.assertEquals(expectedMessage, actualMessage);
	}
}
