package me.nalab.survey.application.service.authorization;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.nalab.core.authorization.spi.Validator;
import me.nalab.survey.application.exception.SurveyDoesNotExistException;
import me.nalab.survey.application.port.out.persistence.authorization.TargetIdFindPort;

@Service
@RequiredArgsConstructor
public class SurveyIdValidator implements Validator {

	private final TargetIdFindPort targetIdFindPort;

	@Override
	@Transactional(readOnly = true)
	public <T, S> boolean valid(T expected, S result) {
		validType(expected, result);
		Long expectedTargetId = (Long)expected;
		Long requestSurveyId = (Long)result;
		Long resultTargetId = targetIdFindPort.findTargetIdBySurveyId(requestSurveyId).orElseThrow(() -> {
			throw new SurveyDoesNotExistException(requestSurveyId);
		});
		System.out.println("expected : " + expectedTargetId + " result : " + resultTargetId);
		return expectedTargetId.equals(resultTargetId);
	}

	private <T, S> void validType(T expected, S result) {
		if(expected instanceof Long && result instanceof Long) {
			return;
		}
		throw new IllegalArgumentException(
			"Expected \"expected\" type was Long \"" + expected.getClass() + "\"Expected \"result\" type was Long \""
				+ result.getClass() + "\"");
	}

}
