package me.nalab.survey.application.common.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import lombok.Setter;
import me.nalab.survey.domain.survey.Choice;
import me.nalab.survey.domain.survey.ChoiceFormQuestion;
import me.nalab.survey.domain.survey.ChoiceFormQuestionType;
import me.nalab.survey.domain.survey.FormQuestionable;
import me.nalab.survey.domain.survey.QuestionType;
import me.nalab.survey.domain.survey.ShortFormQuestion;
import me.nalab.survey.domain.survey.ShortFormQuestionType;
import me.nalab.survey.domain.survey.Survey;

class RandomSurveyFixture {

	@Setter
	private static Supplier<Long> randomIdGenerator;
	@Setter
	private static Supplier<LocalDateTime> randomDateTimeGenerator;
	@Setter
	private static Supplier<Integer> randomQuestionCountGenerator;
	@Setter
	private static Supplier<String> randomStringGenerator;
	@Setter
	private static Supplier<ChoiceFormQuestionType> randomChoiceFormQuestionTypeGenerator;
	@Setter
	private static Supplier<ShortFormQuestionType> randomShortFormQuestionTypeGenerator;
	@Setter
	private static Supplier<Integer> randomChoiceCountGenerator;

	static {
		initGenerator();
	}

	static void initGenerator(){
		randomIdGenerator = () -> 1L;
		randomDateTimeGenerator = LocalDateTime::now;
		randomQuestionCountGenerator = () -> (new Random()).nextInt(10) + 1;
		randomStringGenerator = () -> {
			Random random = new Random();
			return random.ints('0', (int)'z' + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(5)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();
		};
		randomChoiceFormQuestionTypeGenerator = () -> ChoiceFormQuestionType.values()[(new Random()).nextInt(ChoiceFormQuestionType.values().length)];
		randomShortFormQuestionTypeGenerator = () -> ShortFormQuestionType.values()[(new Random()).nextInt(ShortFormQuestionType.values().length)];
		randomChoiceCountGenerator = () -> (new Random()).nextInt(100) + 1;
	}

	static Survey createRandomSurvey() {
		return Survey.builder()
			.id(randomIdGenerator.get())
			.createdAt(randomDateTimeGenerator.get())
			.updatedAt(randomDateTimeGenerator.get())
			.formQuestionableList(getRandomFormQuestionableList())
			.build();
	}

	private static List<FormQuestionable> getRandomFormQuestionableList() {
		List<FormQuestionable> formQuestionableList = new ArrayList<>();
		formQuestionableList.addAll(getRandomChoiceFormQuestionList(1));
		formQuestionableList.addAll(getRandomShortFormQuestionList(formQuestionableList.size()+1));
		return formQuestionableList;
	}

	private static List<ChoiceFormQuestion> getRandomChoiceFormQuestionList(int startOrder) {
		List<ChoiceFormQuestion> randomChoiceFormQuestionList = new ArrayList<>();
		int questionCount = randomQuestionCountGenerator.get();
		for(int i = 0; i < questionCount; i++) {
			List<Choice> choiceList = getRandomChoiceList();
			randomChoiceFormQuestionList.add(
				ChoiceFormQuestion.builder()
					.id(randomIdGenerator.get())
					.title(randomStringGenerator.get())
					.questionType(QuestionType.CHOICE)
					.createdAt(randomDateTimeGenerator.get())
					.updatedAt(randomDateTimeGenerator.get())
					.choiceFormQuestionType(randomChoiceFormQuestionTypeGenerator.get())
					.choiceList(choiceList)
					.maxSelectionCount(choiceList.size())
					.order(startOrder + randomChoiceFormQuestionList.size())
					.build()
			);
		}
		return randomChoiceFormQuestionList;
	}

	private static List<Choice> getRandomChoiceList() {
		int choiceCount = randomChoiceCountGenerator.get();
		List<Choice> choiceList = new ArrayList<>();
		for(int i = 0; i < choiceCount; i++) {
			choiceList.add(
				Choice.builder()
					.id(randomIdGenerator.get())
					.order(choiceList.size() + 1)
					.content(randomStringGenerator.get())
					.build()
			);
		}
		return choiceList;
	}

	private static List<ShortFormQuestion> getRandomShortFormQuestionList(int startOrder) {
		List<ShortFormQuestion> randomShortFormQuestionList = new ArrayList<>();
		int questionCount = randomQuestionCountGenerator.get();
		for(int i = 0; i < questionCount; i++) {
			randomShortFormQuestionList.add(
				ShortFormQuestion.builder()
					.id(randomIdGenerator.get())
					.order(startOrder + randomShortFormQuestionList.size())
					.questionType(QuestionType.SHORT)
					.title(randomStringGenerator.get())
					.createdAt(randomDateTimeGenerator.get())
					.updatedAt(randomDateTimeGenerator.get())
					.shortFormQuestionType(randomShortFormQuestionTypeGenerator.get())
					.build()
			);
		}
		return randomShortFormQuestionList;
	}

}
