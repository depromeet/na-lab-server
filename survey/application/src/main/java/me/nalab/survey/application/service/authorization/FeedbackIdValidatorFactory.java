package me.nalab.survey.application.service.authorization;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.nalab.core.authorization.spi.ValidatorFactory;

@Service
@RequiredArgsConstructor
public class FeedbackIdValidatorFactory implements ValidatorFactory<LongParameterExtractor, FeedbackIdValidator> {

	private final LongParameterExtractor longParameterExtractor;
	private final FeedbackIdValidator feedbackIdValidator;

	@Override
	public LongParameterExtractor parameterExtractor() {
		return longParameterExtractor;
	}

	@Override
	public FeedbackIdValidator validator() {
		return feedbackIdValidator;
	}

}
