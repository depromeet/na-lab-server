package me.nalab.survey.domain.survey;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ChoiceTest {

	@Test
	void testCompareTo() {

		Choice choice1 = Choice.builder()
			.id(1L)
			.content("choice 1")
			.order(2)
			.build();

		Choice choice2 = Choice.builder()
			.id(2L)
			.content("choice 2")
			.order(1)
			.build();

		Choice choice3 = Choice.builder()
			.id(3L)
			.content("choice 3")
			.order(3)
			.build();

		Assertions.assertTrue(choice1.compareTo(choice2) > 0);
		Assertions.assertTrue(choice1.compareTo(choice3) < 0);
		Assertions.assertTrue(choice2.compareTo(choice3) < 0);
	}
}
