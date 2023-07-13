package me.nalab.survey.application.service.authorization;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.nalab.core.authorization.spi.ValidatorFactory;

@Service
@RequiredArgsConstructor
public class FormQuestionFeedbackIdValidatorFactory
	implements ValidatorFactory<LongParameterExtractor, FormQuestionFeedbackIdValidator> {

	private final LongParameterExtractor longParameterExtractor;
	private final FormQuestionFeedbackIdValidator formQuestionFeedbackIdValidator;

	@Override
	public LongParameterExtractor parameterExtractor() {
		return longParameterExtractor;
	}

	@Override
	public FormQuestionFeedbackIdValidator validator() {
		return formQuestionFeedbackIdValidator;
	}
}
