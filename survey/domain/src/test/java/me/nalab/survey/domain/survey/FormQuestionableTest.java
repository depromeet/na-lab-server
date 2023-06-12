package me.nalab.survey.domain.survey;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FormQuestionableTest {

	@Test
	void testCompareTo() {

		List<Choice> choiceList1 = new ArrayList<>();
		choiceList1.add(Choice.builder().id(2L).content("content").order(2).build());
		choiceList1.add(Choice.builder().id(3L).content("content").order(3).build());
		ChoiceFormQuestion question1 = ChoiceFormQuestion.builder()
			.title("Question 1")
			.createdAt(Instant.now())
			.updatedAt(Instant.now())
			.order(1)
			.questionType(QuestionType.CHOICE)
			.choiceList(choiceList1)
			.build();

		List<Choice> choiceList2 = new ArrayList<>();
		choiceList2.add(Choice.builder().id(2L).content("content").order(2).build());
		choiceList2.add(Choice.builder().id(3L).content("content").order(3).build());
		FormQuestionable question2 = ChoiceFormQuestion.builder()
			.title("Question 2")
			.createdAt(Instant.now())
			.updatedAt(Instant.now())
			.order(2)
			.questionType(QuestionType.CHOICE)
			.choiceList(choiceList2)
			.build();

		FormQuestionable question3 = ShortFormQuestion.builder()
			.title("Question 3")
			.createdAt(Instant.now())
			.updatedAt(Instant.now())
			.order(3)
			.questionType(QuestionType.SHORT)
			.build();

		Assertions.assertTrue(question1.compareTo(question2) < 0);
		Assertions.assertTrue(question2.compareTo(question3) < 0);
		Assertions.assertTrue(question1.compareTo(question3) < 0);
	}
}
