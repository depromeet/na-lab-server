package me.nalab.survey.application.common.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.of;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import me.nalab.survey.domain.survey.ChoiceFormQuestionType;
import me.nalab.survey.domain.survey.QuestionType;
import me.nalab.survey.domain.survey.ShortFormQuestionType;

class CommonDtoEnumTest {

	@ParameterizedTest
	@MethodSource("questionTypeDtoSources")
	void QUESTION_TYPE_DTO_CONVERT(QuestionType domainType, QuestionDtoType dtoType, boolean result) {
		assertEquals(result, dtoType == QuestionDtoType.toDtoType(domainType));
		assertEquals(result, domainType == dtoType.toDomainType());
	}

	@ParameterizedTest
	@MethodSource("shortQuestionTypeDtoSources")
	void PERSISTENCE_QUESTION_SHORT_QUESTION_TYPE_CONVERT(ShortFormQuestionType domainType,
		ShortFormQuestionDtoType dtoType, boolean result) {
		assertEquals(result, dtoType == ShortFormQuestionDtoType.toDtoType(domainType));
		assertEquals(result, domainType == dtoType.toDomainType());
	}

	@ParameterizedTest
	@MethodSource("choiceQuestionTypeDtoSources")
	void PERSISTENCE_CHOICE_QUESTION_TYPE_CONVERT(ChoiceFormQuestionType domainType,
		ChoiceFormQuestionDtoType dtoType, boolean result) {
		assertEquals(result, dtoType == ChoiceFormQuestionDtoType.toDtoType(domainType));
		assertEquals(result, domainType == dtoType.toDomainType());
	}

	private static Stream<Arguments> questionTypeDtoSources() {
		return Stream.of(
			of(QuestionType.CHOICE, QuestionDtoType.CHOICE, true),
			of(QuestionType.SHORT, QuestionDtoType.SHORT, true),

			of(QuestionType.CHOICE, QuestionDtoType.SHORT, false),
			of(QuestionType.SHORT, QuestionDtoType.CHOICE, false)
		);
	}

	private static Stream<Arguments> shortQuestionTypeDtoSources() {
		return Stream.of(
			of(ShortFormQuestionType.STRENGTH, ShortFormQuestionDtoType.STRENGTH, true),
			of(ShortFormQuestionType.WEAKNESS, ShortFormQuestionDtoType.WEAKNESS, true),
			of(ShortFormQuestionType.CUSTOM, ShortFormQuestionDtoType.CUSTOM, true),

			of(ShortFormQuestionType.STRENGTH, ShortFormQuestionDtoType.WEAKNESS, false),
			of(ShortFormQuestionType.WEAKNESS, ShortFormQuestionDtoType.STRENGTH, false),
			of(ShortFormQuestionType.CUSTOM, ShortFormQuestionDtoType.STRENGTH, false),
			of(ShortFormQuestionType.STRENGTH, ShortFormQuestionDtoType.CUSTOM, false),
			of(ShortFormQuestionType.WEAKNESS, ShortFormQuestionDtoType.CUSTOM, false),
			of(ShortFormQuestionType.CUSTOM, ShortFormQuestionDtoType.WEAKNESS, false)
		);
	}

	private static Stream<Arguments> choiceQuestionTypeDtoSources() {
		return Stream.of(
			of(ChoiceFormQuestionType.TENDENCY, ChoiceFormQuestionDtoType.TENDENCY, true),
			of(ChoiceFormQuestionType.POSITION, ChoiceFormQuestionDtoType.POSITION, true),
			of(ChoiceFormQuestionType.COLLABORATION_EXPERIENCE, ChoiceFormQuestionDtoType.COLLABORATION_EXPERIENCE,
				true),
			of(ChoiceFormQuestionType.CUSTOM, ChoiceFormQuestionDtoType.CUSTOM, true),

			of(ChoiceFormQuestionType.TENDENCY, ChoiceFormQuestionDtoType.POSITION, false),
			of(ChoiceFormQuestionType.POSITION, ChoiceFormQuestionDtoType.CUSTOM, false),
			of(ChoiceFormQuestionType.COLLABORATION_EXPERIENCE, ChoiceFormQuestionDtoType.TENDENCY, false),
			of(ChoiceFormQuestionType.CUSTOM, ChoiceFormQuestionDtoType.POSITION, false)
		);
	}

}
