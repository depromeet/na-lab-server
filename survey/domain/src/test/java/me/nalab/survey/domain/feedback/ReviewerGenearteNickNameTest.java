package me.nalab.survey.domain.feedback;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReviewerGenearteNickNameTest {

	@Test
	@DisplayName("Reviewer의 이름 생성 테스트 - 2명이 먼저 생성")
	void CREATE_REVIEWER_NICK_NAME_ALREADY_CREATED_TWO() {
		// given
		String expected = "C";
		Reviewer reviewer = Reviewer.builder().build();

		// when
		reviewer.generateNickName("B");

		// then
		assertEquals(expected, reviewer.getNickName());
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

	@Test
	@DisplayName("Reviewer의 이름 생성 테스트 - 경계값")
	void CREATE_REVIEWER_NICK_NAME_CREATE_BOUND(){
		// given
		String expected = "AAAAAAA";
		Reviewer reviewer = Reviewer.builder().build();

		// when
		reviewer.generateNickName("ZZZZZZ");

		// then
		assertEquals(expected, reviewer.getNickName());
	}

	@Test
	@DisplayName("Reviewer의 이름 생성 테스트 - 중간값 변경")
	void CREATE_REVIEWER_NAME_CHANGE_MIDDLE(){
		// given
		String expected = "ZZZBAAA";
		Reviewer reviewer = Reviewer.builder().build();

		// when
		reviewer.generateNickName("ZZZAZZZ");

		// then
		assertEquals(expected, reviewer.getNickName());
	}

}
