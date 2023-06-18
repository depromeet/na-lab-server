package me.nalab.survey.application.service.authorization;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.nalab.core.authorization.spi.ValidatorFactory;

@Service
@RequiredArgsConstructor
public class SurveyIdValidatorFactory implements ValidatorFactory<LongParameterExtractor, SurveyIdValidator> {

	private final SurveyIdValidator surveyIdValidator;
	private final LongParameterExtractor longParameterExtractor;

	@Override
	public LongParameterExtractor parameterExtractor() {
		return longParameterExtractor;
	}

	@Override
	public SurveyIdValidator validator() {
		return surveyIdValidator;
	}

}
