package me.nalab.survey.application.service.authorization;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.nalab.core.authorization.spi.Validator;
import me.nalab.survey.application.exception.FormQuestionFeedbackNotExistException;
import me.nalab.survey.application.port.out.persistence.authorization.TargetIdFindPort;

@Service
@RequiredArgsConstructor
public class FormQuestionFeedbackIdValidator implements Validator {

	private final TargetIdFindPort targetIdFindPort;

	@Override
	@Transactional(readOnly = true)
	public <T, S> boolean valid(T expected, S result) {
		validType(expected, result);
		Long expectedTargetId = (Long)expected;
		Long requestFormQuestionFeedbackId = (Long)result;
		Long resultTargetId = targetIdFindPort.findTargetIdByFormQuestionFeedbackId(requestFormQuestionFeedbackId)
			.orElseThrow(() -> {
				throw new FormQuestionFeedbackNotExistException(requestFormQuestionFeedbackId);
			});
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
