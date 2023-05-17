package me.nalab.survey.domain.survey;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import java.util.logging.Logger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import me.nalab.survey.domain.AbstractSurveySources;
import me.nalab.survey.domain.exception.IdAlreadyGeneratedException;
import me.nalab.survey.domain.survey.valid.DuplicatedOrderException;

class SurveyDomainTest extends AbstractSurveySources {

	private static final Logger LOGGER = Logger.getLogger(SurveyDomainTest.class.getSimpleName());

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

	@Test
	@DisplayName("Survey id 생성 성공 테스트")
	void SURVEY_WITH_ID_SUCCESS() {
		// given
		LongSupplier supplier = () -> 2000L;
		Survey survey = getDefaultSurvey();

		// when
		survey.withId(supplier);

		// then
		assertIsAllIdExpected(2000L, survey);
	}

	@ParameterizedTest
	@MethodSource("surveyWithIdFailSources")
	void SURVEY_WITH_ID_FAIL_DUPLICATED_REQUEST(Function<Supplier<List<FormQuestionable>>, Survey> surveyCreate,
		Supplier<List<FormQuestionable>> formQuestionSupplier) {
		// given
		LongSupplier supplier = () -> 1L;

		// when
		Survey survey = surveyCreate.apply(formQuestionSupplier);

		// then
		assertThrows(IdAlreadyGeneratedException.class, () -> survey.withId(supplier));
	}

	private void assertIsAllIdExpected(Long expected, Survey survey) {
		assertEquals(expected, survey.getId());
		survey.getFormQuestionableList().forEach(
			fq -> {
				assertEquals(expected, fq.getId());
				if(fq.getQuestionType() == QuestionType.CHOICE) {
					((ChoiceFormQuestion)fq).getChoiceList().forEach(fqc -> assertEquals(expected, fqc.getId()));
				}
			}
		);
	}

	private Survey getDefaultSurvey() {
		return Survey.builder()
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.formQuestionableList(
				getDefaultQuestionFormList()
			)
			.build();
	}

	private List<FormQuestionable> getDefaultQuestionFormList() {
		return List.of(
			ShortFormQuestion.builder()
				.title("short")
				.questionType(QuestionType.SHORT)
				.shortFormQuestionType(ShortFormQuestionType.CUSTOM)
				.order(1)
				.createdAt(LocalDateTime.now())
				.updatedAt(LocalDateTime.now())
				.build(),
			ChoiceFormQuestion.builder()
				.title("choice")
				.questionType(QuestionType.CHOICE)
				.choiceFormQuestionType(ChoiceFormQuestionType.CUSTOM)
				.createdAt(LocalDateTime.now())
				.updatedAt(LocalDateTime.now())
				.order(2)
				.maxSelectionCount(1)
				.choiceList(
					getDefaultChoiceList()
				)
				.build()
		);
	}

	private List<Choice> getDefaultChoiceList() {
		return List.of(
			Choice.builder()
				.content("choice1")
				.order(1)
				.build(),
			Choice.builder()
				.content("choce2")
				.order(2)
				.build()
		);
	}

}