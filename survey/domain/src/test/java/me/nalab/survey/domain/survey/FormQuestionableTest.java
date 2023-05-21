package me.nalab.survey.domain.survey;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FormQuestionableTest {

	@Test
	void testCompareTo() {

		ChoiceFormQuestion question1 = ChoiceFormQuestion.builder()
			.title("Question 1")
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.order(1)
			.questionType(QuestionType.CHOICE)
			.choiceList(List.of(Choice.builder().id(1L).content("content").order(1).build(),
				Choice.builder().id(2L).content("content").order(2).build(),
				Choice.builder().id(3L).content("content").order(3).build()))
			.build();

		FormQuestionable question2 = ChoiceFormQuestion.builder()
			.title("Question 2")
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.order(2)
			.questionType(QuestionType.CHOICE)
			.choiceList(List.of(Choice.builder().id(1L).content("content").order(1).build(),
				Choice.builder().id(2L).content("content").order(2).build(),
				Choice.builder().id(3L).content("content").order(3).build()))
			.build();

		FormQuestionable question3 = ShortFormQuestion.builder()
			.title("Question 3")
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.order(3)
			.questionType(QuestionType.SHORT)
			.build();

		Assertions.assertTrue(question1.compareTo(question2) < 0);
		Assertions.assertTrue(question2.compareTo(question3) < 0);
		Assertions.assertTrue(question1.compareTo(question3) < 0);
	}
}
