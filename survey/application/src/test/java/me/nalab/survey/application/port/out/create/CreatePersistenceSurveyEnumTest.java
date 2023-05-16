package me.nalab.survey.application.port.out.create;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.of;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import me.nalab.survey.application.port.out.persistence.create.request.PersistenceChoiceFormQuestionType;
import me.nalab.survey.application.port.out.persistence.create.request.PersistenceQuestionType;
import me.nalab.survey.application.port.out.persistence.create.request.PersistenceShortFormQuestionType;
import me.nalab.survey.domain.survey.ChoiceFormQuestionType;
import me.nalab.survey.domain.survey.QuestionType;
import me.nalab.survey.domain.survey.ShortFormQuestionType;

class CreatePersistenceSurveyEnumTest {

	@ParameterizedTest
	@MethodSource("persistenceQuestionTypeSources")
	void PERSISTENCE_QUESTION_TYPE_CONVERT(QuestionType questionType
		, PersistenceQuestionType expected
		, boolean result) {
		assertEquals(result, expected == PersistenceQuestionType.convert(questionType));
	}

	@ParameterizedTest
	@MethodSource("persistenceShortQuestionTypeSources")
	void PERSISTENCE_QUESTION_SHORT_QUESTION_TYPE_CONVERT(ShortFormQuestionType shortFormQuestionType
		, PersistenceShortFormQuestionType expected
		, boolean result) {
		assertEquals(result, expected == PersistenceShortFormQuestionType.convert(shortFormQuestionType));
	}

	@ParameterizedTest
	@MethodSource("persistenceChoiceQuestionTypeSources")
	void PERSISTENCE_CHOICE_QUESTION_TYPE_CONVERT(ChoiceFormQuestionType choiceFormQuestionType
		, PersistenceChoiceFormQuestionType expected
		, boolean result) {
		assertEquals(result, expected == PersistenceChoiceFormQuestionType.convert(choiceFormQuestionType));
	}

	private static Stream<Arguments> persistenceQuestionTypeSources() {
		return Stream.of(
			of(QuestionType.CHOICE, PersistenceQuestionType.CHOICE, true)
			, of(QuestionType.SHORT, PersistenceQuestionType.SHORT, true)

			, of(QuestionType.CHOICE, PersistenceQuestionType.SHORT, false)
			, of(QuestionType.SHORT, PersistenceQuestionType.CHOICE, false)
		);
	}

	private static Stream<Arguments> persistenceShortQuestionTypeSources() {
		return Stream.of(
			of(ShortFormQuestionType.STRENGTH, PersistenceShortFormQuestionType.STRENGTH, true)
			, of(ShortFormQuestionType.WEAKNESS, PersistenceShortFormQuestionType.WEAKNESS, true)
			, of(ShortFormQuestionType.CUSTOM, PersistenceShortFormQuestionType.CUSTOM, true)

			, of(ShortFormQuestionType.STRENGTH, PersistenceShortFormQuestionType.WEAKNESS, false)
			, of(ShortFormQuestionType.WEAKNESS, PersistenceShortFormQuestionType.STRENGTH, false)
			, of(ShortFormQuestionType.CUSTOM, PersistenceShortFormQuestionType.STRENGTH, false)
			, of(ShortFormQuestionType.STRENGTH, PersistenceShortFormQuestionType.CUSTOM, false)
			, of(ShortFormQuestionType.WEAKNESS, PersistenceShortFormQuestionType.CUSTOM, false)
			, of(ShortFormQuestionType.CUSTOM, PersistenceShortFormQuestionType.WEAKNESS, false)
		);
	}

	private static Stream<Arguments> persistenceChoiceQuestionTypeSources() {
		return Stream.of(
			of(ChoiceFormQuestionType.TENDENCY, PersistenceChoiceFormQuestionType.TENDENCY, true)
			, of(ChoiceFormQuestionType.POSITION, PersistenceChoiceFormQuestionType.POSITION, true)
			, of(ChoiceFormQuestionType.COLLABORATION_EXPERIENCE
				, PersistenceChoiceFormQuestionType.COLLABORATION_EXPERIENCE, true)
			, of(ChoiceFormQuestionType.CUSTOM, PersistenceChoiceFormQuestionType.CUSTOM, true)

			, of(ChoiceFormQuestionType.TENDENCY, PersistenceChoiceFormQuestionType.POSITION, false)
			, of(ChoiceFormQuestionType.POSITION, PersistenceChoiceFormQuestionType.CUSTOM, false)
			, of(ChoiceFormQuestionType.COLLABORATION_EXPERIENCE, PersistenceChoiceFormQuestionType.TENDENCY, false)
			, of(ChoiceFormQuestionType.CUSTOM, PersistenceChoiceFormQuestionType.POSITION, false)
		);
	}

}
