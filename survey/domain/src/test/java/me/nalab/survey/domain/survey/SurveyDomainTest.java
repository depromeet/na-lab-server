package me.nalab.survey.domain.survey;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.stream.IntStream;

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

	@ParameterizedTest
	@MethodSource("surveyCreateSuccessSources")
	void SURVEY_QUESTION_AND_CHOICELIST_ORDER_VALIDATION_TEST(
		Function<Supplier<List<FormQuestionable>>, Survey> surveyCreate,
		Supplier<List<FormQuestionable>> formQuestionSupplier) {

		Survey survey = surveyCreate.apply(formQuestionSupplier);
		assertTrue(isFormQuestionableListSorted(survey.getFormQuestionableList()));
	}

	private boolean isFormQuestionableListSorted(List<FormQuestionable> formQuestionableList) {
		return IntStream.range(1, formQuestionableList.size())
			.allMatch(i -> formQuestionableList.get(i - 1).getOrder() < formQuestionableList.get(i).getOrder())
			&& formQuestionableList.stream().allMatch(this::isChoiceFormQuestionListIsSorted);
	}

	private boolean isChoiceFormQuestionListIsSorted(FormQuestionable formQuestionable) {
		if(formQuestionable instanceof ChoiceFormQuestion) {
			ChoiceFormQuestion choiceFormQuestion = (ChoiceFormQuestion)formQuestionable;
			List<Choice> choiceList = choiceFormQuestion.getChoiceList();
			return IntStream.range(1, choiceList.size())
				.allMatch(i -> choiceList.get(i - 1).getOrder() < choiceList.get(i).getOrder());
		}
		return true;
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
			.createdAt(Instant.now())
			.updatedAt(Instant.now())
			.formQuestionableList(
				getDefaultQuestionFormList()
			)
			.build();
	}

	private List<FormQuestionable> getDefaultQuestionFormList() {
		List<FormQuestionable> formQuestionableList = new ArrayList<>();
		formQuestionableList.add(ShortFormQuestion.builder()
			.title("short")
			.questionType(QuestionType.SHORT)
			.shortFormQuestionType(ShortFormQuestionType.CUSTOM)
			.order(1)
			.createdAt(Instant.now())
			.updatedAt(Instant.now())
			.build());
		formQuestionableList.add(ChoiceFormQuestion.builder()
			.title("choice")
			.questionType(QuestionType.CHOICE)
			.choiceFormQuestionType(ChoiceFormQuestionType.CUSTOM)
			.createdAt(Instant.now())
			.updatedAt(Instant.now())
			.order(2)
			.maxSelectionCount(1)
			.choiceList(
				getDefaultChoiceList()
			)
			.build());
		return formQuestionableList;
	}

	private List<Choice> getDefaultChoiceList() {
		List<Choice> choiceList = new ArrayList<>();
		choiceList.add(Choice.builder()
			.content("choice1")
			.order(1)
			.build());
		choiceList.add(Choice.builder()
			.content("choce2")
			.order(2)
			.build());
		return choiceList;
	}

}
