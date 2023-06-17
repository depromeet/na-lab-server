package me.nalab.survey.application.port.in.authorization;

import me.nalab.core.authorization.spi.ParameterExtractor;
import me.nalab.core.authorization.spi.Validator;
import me.nalab.core.authorization.spi.ValidatorFactory;
import me.nalab.survey.application.service.authorization.FeedbackIdValidatorFactory;
import me.nalab.survey.application.service.authorization.SurveyIdValidatorFactory;

public final class SurveyAuthorization {

	public static final Class<? extends ValidatorFactory<? extends ParameterExtractor, ? extends Validator>> SURVEY_ID_VALIDATOR = SurveyIdValidatorFactory.class;
	public static final Class<? extends ValidatorFactory<? extends ParameterExtractor, ? extends Validator>> FEEDBACK_ID_VALIDATOR = FeedbackIdValidatorFactory.class;

	private SurveyAuthorization() {
		throw new UnsupportedOperationException("Cannot invoke constructor \"SurveyAuthorization()\"");
	}

}
