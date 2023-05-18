package me.nalab.survey.application;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import lombok.Setter;
import me.nalab.survey.application.common.dto.ChoiceDto;
import me.nalab.survey.application.common.dto.ChoiceFormQuestionDto;
import me.nalab.survey.application.common.dto.ChoiceFormQuestionDtoType;
import me.nalab.survey.application.common.dto.FormQuestionDtoable;
import me.nalab.survey.application.common.dto.QuestionDtoType;
import me.nalab.survey.application.common.dto.ShortFormQuestionDto;
import me.nalab.survey.application.common.dto.ShortFormQuestionDtoType;
import me.nalab.survey.application.common.dto.SurveyDto;

public class RandomSurveyDtoFixture {

	@Setter
	private static Supplier<Long> randomIdGenerator;
	@Setter
	private static Supplier<LocalDateTime> randomDateTimeGenerator;
	@Setter
	private static Supplier<Integer> randomQuestionCountGenerator;
	@Setter
	private static Supplier<String> randomStringGenerator;
	@Setter
	private static Supplier<ChoiceFormQuestionDtoType> randomChoiceFormQuestionDtoTypeGenerator;
	@Setter
	private static Supplier<ShortFormQuestionDtoType> randomShortFormQuestionDtoTypeGenerator;
	@Setter
	private static Supplier<Integer> randomChoiceDtoCountGenerator;

	static {
		initGenerator();
	}

	public static void initGenerator() {
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
		randomChoiceFormQuestionDtoTypeGenerator = () -> ChoiceFormQuestionDtoType.values()[(new Random()).nextInt(
			ChoiceFormQuestionDtoType.values().length)];
		randomShortFormQuestionDtoTypeGenerator = () -> ShortFormQuestionDtoType.values()[(new Random()).nextInt(
			ShortFormQuestionDtoType.values().length)];
		randomChoiceDtoCountGenerator = () -> (new Random()).nextInt(100) + 1;
	}

	public static SurveyDto createRandomSurveyDto() {
		return SurveyDto.builder()
			.id(randomIdGenerator.get())
			.targetId(randomIdGenerator.get())
			.createdAt(randomDateTimeGenerator.get())
			.updatedAt(randomDateTimeGenerator.get())
			.formQuestionDtoableList(getRandomFormQuestionDtoableList())
			.build();
	}

	private static List<FormQuestionDtoable> getRandomFormQuestionDtoableList() {
		List<FormQuestionDtoable> formQuestionDtoableList = new ArrayList<>();
		formQuestionDtoableList.addAll(getRandomChoiceFormQuestionDtoList(1));
		formQuestionDtoableList.addAll(getRandomShortFormQuestionDtoList(formQuestionDtoableList.size() + 1));
		return formQuestionDtoableList;
	}

	private static List<ChoiceFormQuestionDto> getRandomChoiceFormQuestionDtoList(int startOrder) {
		List<ChoiceFormQuestionDto> randomChoiceFormQuestionList = new ArrayList<>();
		int questionCount = randomQuestionCountGenerator.get();
		for(int i = 0; i < questionCount; i++) {
			List<ChoiceDto> choiceDtoList = getRandomChoiceDtoList();
			randomChoiceFormQuestionList.add(
				ChoiceFormQuestionDto.builder()
					.id(randomIdGenerator.get())
					.title(randomStringGenerator.get())
					.questionDtoType(QuestionDtoType.CHOICE)
					.createdAt(randomDateTimeGenerator.get())
					.updatedAt(randomDateTimeGenerator.get())
					.choiceFormQuestionDtoType(randomChoiceFormQuestionDtoTypeGenerator.get())
					.choiceDtoList(choiceDtoList)
					.maxSelectionCount(choiceDtoList.size())
					.order(startOrder + randomChoiceFormQuestionList.size())
					.build()
			);
		}
		return randomChoiceFormQuestionList;
	}

	private static List<ChoiceDto> getRandomChoiceDtoList() {
		int choiceCount = randomChoiceDtoCountGenerator.get();
		List<ChoiceDto> choiceList = new ArrayList<>();
		for(int i = 0; i < choiceCount; i++) {
			choiceList.add(
				ChoiceDto.builder()
					.id(randomIdGenerator.get())
					.order(choiceList.size() + 1)
					.content(randomStringGenerator.get())
					.build()
			);
		}
		return choiceList;
	}

	private static List<ShortFormQuestionDto> getRandomShortFormQuestionDtoList(int startOrder) {
		List<ShortFormQuestionDto> randomShortFormQuestionList = new ArrayList<>();
		int questionCount = randomQuestionCountGenerator.get();
		for(int i = 0; i < questionCount; i++) {
			randomShortFormQuestionList.add(
				ShortFormQuestionDto.builder()
					.id(randomIdGenerator.get())
					.order(startOrder + randomShortFormQuestionList.size())
					.questionDtoType(QuestionDtoType.SHORT)
					.title(randomStringGenerator.get())
					.createdAt(randomDateTimeGenerator.get())
					.updatedAt(randomDateTimeGenerator.get())
					.shortFormQuestionDtoType(randomShortFormQuestionDtoTypeGenerator.get())
					.build()
			);
		}
		return randomShortFormQuestionList;
	}

}
