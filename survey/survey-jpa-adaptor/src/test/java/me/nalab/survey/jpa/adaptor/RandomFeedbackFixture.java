package me.nalab.survey.jpa.adaptor;

import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import lombok.Setter;
import me.nalab.core.time.TimeUtil;
import me.nalab.survey.domain.feedback.Bookmark;
import me.nalab.survey.domain.feedback.ChoiceFormQuestionFeedback;
import me.nalab.survey.domain.feedback.Feedback;
import me.nalab.survey.domain.feedback.FormQuestionFeedbackable;
import me.nalab.survey.domain.feedback.Reviewer;
import me.nalab.survey.domain.feedback.ShortFormQuestionFeedback;
import me.nalab.survey.domain.survey.ChoiceFormQuestion;
import me.nalab.survey.domain.survey.FormQuestionable;
import me.nalab.survey.domain.survey.QuestionType;
import me.nalab.survey.domain.survey.ShortFormQuestion;
import me.nalab.survey.domain.survey.Survey;

public class RandomFeedbackFixture {

	@Setter
	private static Supplier<Long> randomIdGenerator;

	@Setter
	private static BooleanSupplier randomBooleanGenerator;

	@Setter
	private static Supplier<String> randomStringGenerator;

	static {
		randomIdGenerator = new Supplier<>() {
			private Long id = 0L;

			@Override
			public Long get() {
				id++;
				return id;
			}
		};

		randomBooleanGenerator = new BooleanSupplier() {
			private final Random random = new Random();

			@Override
			public boolean getAsBoolean() {
				return random.nextBoolean();
			}
		};

		randomStringGenerator = () -> {
			Random random = new Random();
			return random.ints('0', (int)'z' + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(5)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();
		};
	}

	public static Feedback getRandomFeedbackBySurvey(Survey survey) {
		return Feedback.builder()
			.id(randomIdGenerator.get())
			.surveyId(survey.getId())
			.isRead(randomBooleanGenerator.getAsBoolean())
			.reviewer(getRandomReviewer())
			.formQuestionFeedbackableList(getRandomQuestionFeedbackListBySurvey(survey))
			.createdAt(survey.getCreatedAt().plus(1, ChronoUnit.DAYS))
			.updatedAt(survey.getUpdatedAt().plus(1, ChronoUnit.DAYS))
			.build();
	}

	private static Reviewer getRandomReviewer() {
		return Reviewer.builder()
			.id(randomIdGenerator.get())
			.nickName(randomStringGenerator.get())
			.collaborationExperience(randomBooleanGenerator.getAsBoolean())
			.position(randomStringGenerator.get())
			.build();
	}

	private static List<FormQuestionFeedbackable> getRandomQuestionFeedbackListBySurvey(Survey survey) {
		List<FormQuestionable> formQuestionableList = survey.getFormQuestionableList();
		return formQuestionableList.stream().map(q -> {
			if (q.getQuestionType() == QuestionType.CHOICE) {
				return getRandomChoiceFormQuestionFeedback((ChoiceFormQuestion)q);
			}
			return getRandomShortFormQuestionFeedback((ShortFormQuestion)q);
		}).collect(Collectors.toList());
	}

	private static ChoiceFormQuestionFeedback getRandomChoiceFormQuestionFeedback(
		ChoiceFormQuestion choiceFormQuestion) {
		Set<Long> selectedIdSet = new HashSet<>();
		for (int i = 0; i < choiceFormQuestion.getMaxSelectableCount(); i++) {
			selectedIdSet.add(choiceFormQuestion.getChoiceList().get(i).getId());
		}
		return ChoiceFormQuestionFeedback.builder()
			.id(choiceFormQuestion.getId())
			.questionId(choiceFormQuestion.getId())
			.isRead(randomBooleanGenerator.getAsBoolean())
			.bookmark(Bookmark.builder()
				.isBookmarked(false)
				.bookmarkedAt(TimeUtil.toInstant())
				.build())
			.selectedChoiceIdSet(selectedIdSet)
			.build();
	}

	private static ShortFormQuestionFeedback getRandomShortFormQuestionFeedback(
		ShortFormQuestion shortFormQuestion) {
		return ShortFormQuestionFeedback.builder()
			.id(shortFormQuestion.getId())
			.questionId(shortFormQuestion.getId())
			.isRead(randomBooleanGenerator.getAsBoolean())
			.bookmark(Bookmark.builder()
				.isBookmarked(false)
				.bookmarkedAt(TimeUtil.toInstant())
				.build())
			.replyList(List.of(randomStringGenerator.get()))
			.build();
	}
}
