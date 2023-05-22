package me.nalab.survey.domain.feedback;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ReviewerGenerateNickNameTest {

	@ParameterizedTest
	@MethodSource("reviewerNameSources")
	void CREATE_REVIEWER_NAME(String expected, String generate) {
		// given
		Reviewer reviewer = Reviewer.builder().build();

		// when
		reviewer.generateNickName(generate);

		// then
		assertEquals(expected, reviewer.getNickName());
	}

	private static Stream<Arguments> reviewerNameSources() {
		return Stream.of(
			Arguments.of("C", "B"),
			Arguments.of("AAAAAAA", "ZZZZZZ"),
			Arguments.of("ZZZBAAA", "ZZZAZZZ")
		);
	}

	@Test
	@DisplayName("Reviewer의 이름 생성 테스트 - 첫번째로 생성 됨")
	void CREATE_REVIEWER_NICK_NAME_CREATE_FIRST() {
		// given
		String expected = "A";
		Reviewer reviewer = Reviewer.builder().build();

		// when
		reviewer.generateFirstReviewerNickName();

		// then
		assertEquals(expected, reviewer.getNickName());
	}

}
