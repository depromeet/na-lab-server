package me.nalab.survey.domain.survey;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Logger;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import me.nalab.survey.domain.AbstractSurveySources;

class AbstractSurveyDomainTest extends AbstractSurveySources {

	private static final Logger LOGGER = Logger.getLogger(AbstractSurveyDomainTest.class.getSimpleName());

	@ParameterizedTest
	@MethodSource("surveyCreateSuccessSources")
	void SURVEY_CREATE_SUCCESS(Function<Supplier<List<FormQuestionable>>, Survey> surveyCreate,
		Supplier<List<FormQuestionable>> formQuestionSupplier) {

		assertDoesNotThrow(() -> surveyCreate.apply(formQuestionSupplier));
	}

	@ParameterizedTest
	@MethodSource("surveyCreateSuccessSources")
	void SURVEY_TO_STRING_LOGGING_SUCCESS(Function<Supplier<List<FormQuestionable>>, Survey> surveyCreate,
		Supplier<List<FormQuestionable>> formQuestionSupplier) {

		Survey survey = surveyCreate.apply(formQuestionSupplier);
		assertNotNull(survey);
		LOGGER.info(survey.toString());
	}

}
