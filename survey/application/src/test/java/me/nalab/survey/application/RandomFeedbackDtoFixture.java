package me.nalab.survey.application;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import lombok.Setter;
import me.nalab.survey.application.common.feedback.dto.ChoiceFormQuestionFeedbackDto;
import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.feedback.dto.FormQuestionFeedbackDtoable;
import me.nalab.survey.application.common.feedback.dto.ReviewerDto;
import me.nalab.survey.application.common.feedback.dto.ShortFormQuestionFeedbackDto;
import me.nalab.survey.domain.survey.ChoiceFormQuestion;
import me.nalab.survey.domain.survey.FormQuestionable;
import me.nalab.survey.domain.survey.QuestionType;
import me.nalab.survey.domain.survey.ShortFormQuestion;
import me.nalab.survey.domain.survey.Survey;

public class RandomFeedbackDtoFixture {

	@Setter
	private static BooleanSupplier RANDOM_BOOLEAN_SUPPLIER;

	@Setter
	private static Supplier<String> RANDOM_STRING_SUPPLIER;

	static {
		RANDOM_BOOLEAN_SUPPLIER = new BooleanSupplier() {
			private final Random random = new Random();

			@Override
			public boolean getAsBoolean() {
				return random.nextBoolean();
			}
		};

		RANDOM_STRING_SUPPLIER = () -> {
			Random random = new Random();
			return random.ints('0', (int)'z' + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(5)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();
		};
	}

	public static FeedbackDto getRandomFeedbackDtoBySurvey(Survey survey) {
		return FeedbackDto.builder()
			.surveyId(survey.getId())
			.isRead(RANDOM_BOOLEAN_SUPPLIER.getAsBoolean())
			.reviewerDto(getRandomReviewerDto())
			.formQuestionFeedbackDtoableList(getRandomQuestionFeedbackDtoListBySurvey(survey))
			.createdAt(survey.getCreatedAt().plusDays(1))
			.updatedAt(survey.getUpdatedAt().plusDays(1))
			.build();
	}

	private static ReviewerDto getRandomReviewerDto() {
		return ReviewerDto.builder()
			.collaborationExperience(RANDOM_BOOLEAN_SUPPLIER.getAsBoolean())
			.position(RANDOM_STRING_SUPPLIER.get())
			.build();
	}

	private static List<FormQuestionFeedbackDtoable> getRandomQuestionFeedbackDtoListBySurvey(Survey survey) {
		List<FormQuestionable> formQuestionableList = survey.getFormQuestionableList();
		return formQuestionableList.stream().map(q -> {
			if(q.getQuestionType() == QuestionType.CHOICE) {
				return getRandomChoiceFormQuestionFeedbackDto((ChoiceFormQuestion)q);
			}
			return getRandomShortFormQuestionFeedbackDto((ShortFormQuestion)q);
		}).collect(Collectors.toList());
	}

	private static ChoiceFormQuestionFeedbackDto getRandomChoiceFormQuestionFeedbackDto(
		ChoiceFormQuestion choiceFormQuestion) {
		Set<Long> selectedIdSet = new HashSet<>();
		for(int i = 0; i < choiceFormQuestion.getMaxSelectableCount(); i++) {
			selectedIdSet.add(choiceFormQuestion.getChoiceList().get(i).getId());
		}
		return ChoiceFormQuestionFeedbackDto.builder()
			.questionId(choiceFormQuestion.getId())
			.isRead(RANDOM_BOOLEAN_SUPPLIER.getAsBoolean())
			.selectedChoiceIdSet(selectedIdSet)
			.build();
	}

	private static ShortFormQuestionFeedbackDto getRandomShortFormQuestionFeedbackDto(
		ShortFormQuestion shortFormQuestion) {
		return ShortFormQuestionFeedbackDto.builder()
			.questionId(shortFormQuestion.getId())
			.isRead(RANDOM_BOOLEAN_SUPPLIER.getAsBoolean())
			.replyList(List.of(RANDOM_STRING_SUPPLIER.get()))
			.build();
	}
}
