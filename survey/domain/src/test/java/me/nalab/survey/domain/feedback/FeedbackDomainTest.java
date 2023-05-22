package me.nalab.survey.domain.feedback;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.LongSupplier;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import me.nalab.survey.domain.RandomSurveyFixture;
import me.nalab.survey.domain.exception.IdAlreadyGeneratedException;
import me.nalab.survey.domain.exception.IllegalFeedbackException;
import me.nalab.survey.domain.exception.IllegalQuestionFeedbackException;
import me.nalab.survey.domain.survey.Choice;
import me.nalab.survey.domain.survey.ChoiceFormQuestion;
import me.nalab.survey.domain.survey.FormQuestionable;
import me.nalab.survey.domain.survey.QuestionType;
import me.nalab.survey.domain.survey.ShortFormQuestion;
import me.nalab.survey.domain.survey.Survey;

class FeedbackDomainTest {

	private static final LongSupplier LONG_SUPPLIER = new LongSupplier() {
		private Long id = 0L;

		@Override
		public long getAsLong() {
			id++;
			return id;
		}
	};

	@Test
	@DisplayName("Survey에 Feedback 남기기 성공 테스트")
	void FEEDBACK_SUCCESS() {
		// given
		Survey survey = RandomSurveyFixture.createRandomSurvey();
		List<Feedback> feedbackList = List.of(
			getFeedback(survey, "hello", true, "designer"),
			getFeedback(survey, "world", false, "product-manager"),
			getFeedback(survey, "hello", false, "developer")
		);

		feedbackList.forEach(f -> f.withId(LONG_SUPPLIER));

		// when & then
		feedbackList.forEach(f -> assertDoesNotThrow(() -> survey.throwIfIsNotValidFeedback(f)));
	}

	@Test
	@DisplayName("Survey에 Feedback 남기기 실패 테스트 - Survey가 다름")
	void FEEDBACK_FAIL_DIFFERENT_SURVEY() {
		// given
		Survey survey = RandomSurveyFixture.createRandomSurvey();
		Survey wrongSurvey = RandomSurveyFixture.createRandomSurvey();
		Feedback feedback = getFeedback(wrongSurvey, "hello", true, "designer");

		feedback.withId(LONG_SUPPLIER);

		// when & then
		assertThrows(IllegalFeedbackException.class, () -> survey.throwIfIsNotValidFeedback(feedback));
	}

	@Test
	@DisplayName("feedback 남기기 실패 테스트 - ChoiceFormQuestion이 잘못됨")
	void FEEDBACK_FAIL_WRONG_CHOICE_FORM_QUESTION() {
		// given
		Survey survey = RandomSurveyFixture.createRandomSurvey();

		Feedback wrongFeedback = getFeedbackWithWrongFormQuestionFeedback(survey, "groot", false, "sleep");

		// when & then
		assertThrows(IllegalQuestionFeedbackException.class, () -> survey.throwIfIsNotValidFeedback(wrongFeedback));
	}

	@Test
	@DisplayName("ChoiceFormQuestionFeedback valid fail - 다른 타입의 questionform이 들어왔을 때")
	void FEEDBACK_FAIL_DIFFERENT_TYPE_FORM() {
		// given
		Survey survey = RandomSurveyFixture.createRandomSurvey();

		ChoiceFormQuestionFeedback choiceFormQuestionFeedback = ChoiceFormQuestionFeedback.builder()
			.questionId(1L)
			.isRead(false)
			.selectedChoiceIdSet(Set.of(1L, 2L, 3L))
			.build();

		FormQuestionable shortFormQuestion = survey.getFormQuestionableList()
			.stream()
			.filter(f -> f.getQuestionType() == QuestionType.SHORT)
			.findAny()
			.orElse(null);

		// when & then
		assertNotNull(shortFormQuestion);
		assertFalse(choiceFormQuestionFeedback.isValidQuestionFeedback(shortFormQuestion));
	}

	@Test
	@DisplayName("ChoiceFormQuestionFeedback valid fail - 선택가능한 수 보다 많이 선택했을때")
	void FEEDBACK_FAIL_CHOICE_OVERFLOW() {
		// given
		Survey survey = RandomSurveyFixture.createRandomSurvey();

		ChoiceFormQuestion choiceFormQuestion = (ChoiceFormQuestion)survey.getFormQuestionableList()
			.stream()
			.filter(f -> f.getQuestionType() == QuestionType.CHOICE)
			.findAny()
			.orElse(null);

		Set<Long> selectedChoiceIdSet = Objects.requireNonNull(choiceFormQuestion).getChoiceList()
			.stream()
			.map(Choice::getId)
			.collect(Collectors.toSet());
		selectedChoiceIdSet.add(1000L);

		ChoiceFormQuestionFeedback choiceFormQuestionFeedback = ChoiceFormQuestionFeedback.builder()
			.questionId(1L)
			.isRead(false)
			.selectedChoiceIdSet(selectedChoiceIdSet)
			.build();

		// when & then
		assertFalse(choiceFormQuestionFeedback.isValidQuestionFeedback(choiceFormQuestion));
	}

	@Test
	@DisplayName("feedback domain들 - id generate fail 테스트")
	void FEEDBACK_ID_GENERATE_FAIL() {
		// given
		Feedback feedback = Feedback.builder().formQuestionFeedbackableList(List.of()).build();
		Reviewer reviewer = Reviewer.builder().build();
		ShortFormQuestionFeedback shortFormQuestionFeedback = ShortFormQuestionFeedback.builder().build();
		ChoiceFormQuestionFeedback choiceFormQuestionFeedback = ChoiceFormQuestionFeedback.builder().build();

		// when
		feedback.withId(LONG_SUPPLIER);
		reviewer.withId(LONG_SUPPLIER);
		shortFormQuestionFeedback.withId(LONG_SUPPLIER);
		choiceFormQuestionFeedback.withId(LONG_SUPPLIER);

		// then
		assertThrows(IdAlreadyGeneratedException.class, () -> feedback.withId(LONG_SUPPLIER));
		assertThrows(IdAlreadyGeneratedException.class, () -> reviewer.withId(LONG_SUPPLIER));
		assertThrows(IdAlreadyGeneratedException.class, () -> shortFormQuestionFeedback.withId(LONG_SUPPLIER));
		assertThrows(IdAlreadyGeneratedException.class, () -> choiceFormQuestionFeedback.withId(LONG_SUPPLIER));
	}

	private Feedback getFeedback(Survey survey, String name, boolean isCollaborated, String position) {
		return Feedback.builder()
			.surveyId(survey.getId())
			.reviewer(getReviewer(name, isCollaborated, position))
			.formQuestionFeedbackableList(getFormQuestionFeedbackList(survey))
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.isRead(false)
			.build();
	}

	private Feedback getFeedbackWithWrongFormQuestionFeedback(Survey survey, String name, boolean isCollaborated,
		String position) {
		ChoiceFormQuestionFeedback choiceFormQuestionFeedback = ChoiceFormQuestionFeedback.builder()
			.questionId(1L)
			.isRead(false)
			.selectedChoiceIdSet(Set.of(-1L, -2L, -3L))
			.build();
		return Feedback.builder()
			.surveyId(survey.getId())
			.reviewer(getReviewer(name, isCollaborated, position))
			.formQuestionFeedbackableList(List.of(choiceFormQuestionFeedback))
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.isRead(false)
			.build();
	}

	private Reviewer getReviewer(String nickName, boolean isCollaborated, String position) {
		return Reviewer.builder()
			.nickName(nickName)
			.collaborationExperience(isCollaborated)
			.position(position)
			.build();
	}

	private List<FormQuestionFeedbackable> getFormQuestionFeedbackList(Survey survey) {
		return survey.getFormQuestionableList().stream().map(
			q -> {
				if(q instanceof ShortFormQuestion) {
					return getShortFormQuestionFeedback((ShortFormQuestion)q);
				}
				return getChoiceFormQuestionFeedback((ChoiceFormQuestion)q);
			}
		).collect(Collectors.toList());
	}

	private ShortFormQuestionFeedback getShortFormQuestionFeedback(ShortFormQuestion shortFormQuestion) {
		return ShortFormQuestionFeedback.builder()
			.questionId(shortFormQuestion.getId())
			.isRead(false)
			.replyList(List.of("hello world", "na lab"))
			.build();
	}

	private ChoiceFormQuestionFeedback getChoiceFormQuestionFeedback(ChoiceFormQuestion choiceFormQuestion) {
		return ChoiceFormQuestionFeedback.builder()
			.questionId(choiceFormQuestion.getId())
			.isRead(false)
			.selectedChoiceIdSet(getChoiceIdSet(choiceFormQuestion))
			.build();
	}

	private Set<Long> getChoiceIdSet(ChoiceFormQuestion choiceFormQuestion) {
		return choiceFormQuestion.getChoiceList().stream()
			.map(Choice::getId)
			.collect(Collectors.toSet());
	}

}
