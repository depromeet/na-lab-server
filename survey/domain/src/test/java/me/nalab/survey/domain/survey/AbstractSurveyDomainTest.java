package me.nalab.survey.domain.survey;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Logger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import me.nalab.survey.domain.AbstractSurveySources;
import me.nalab.survey.domain.survey.valid.DuplicatedOrderException;

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

	@ParameterizedTest
	@MethodSource("surveyCreateFailOrderSources")
	@DisplayName("Survey 생성 실패 테스트 - 중복 Order")
	void SURVEY_WITH_DEFAULT_QUESTION_FORM_FAIL_DUPLICATED_ID(
		Function<Supplier<List<FormQuestionable>>, Survey> surveyCreate
		, Supplier<List<FormQuestionable>> formQuestionSupplier
	) {
		assertThrows(DuplicatedOrderException.class, () -> surveyCreate.apply(formQuestionSupplier));
	}

}
