package me.nalab.survey.web.adaptor.findspecific;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import me.nalab.survey.application.common.feedback.dto.ChoiceFormQuestionFeedbackDto;
import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.feedback.dto.ReviewerDto;
import me.nalab.survey.application.common.feedback.dto.ShortFormQuestionFeedbackDto;
import me.nalab.survey.application.common.survey.dto.ChoiceDto;
import me.nalab.survey.application.common.survey.dto.ChoiceFormQuestionDto;
import me.nalab.survey.application.common.survey.dto.QuestionDtoType;
import me.nalab.survey.application.common.survey.dto.ShortFormQuestionDto;
import me.nalab.survey.application.common.survey.dto.SurveyDto;
import me.nalab.survey.web.adaptor.findspecific.response.ChoiceFormFeedbackResponse;
import me.nalab.survey.web.adaptor.findspecific.response.ChoiceResponse;
import me.nalab.survey.web.adaptor.findspecific.response.ReviewerResponse;
import me.nalab.survey.web.adaptor.findspecific.response.ShortFormFeedbackResponse;
import me.nalab.survey.web.adaptor.findspecific.response.SpecificFeedbackResponse;

class SpecificFeedbackResponseMapperTest {

	@Test
	@DisplayName("개별 응답 조회 mapper test")
	void SPECIFIC_FEEDBACK_RESPONSE_MAPPER_TEST() {
		// given
		SurveyDto surveyDto = getSurveyDto();
		FeedbackDto feedbackDto = getFeedbackDto();
		SpecificFeedbackResponse expectedSpecificFeedbackResponse = getSpecificFeedbackResponse();

		// when
		SpecificFeedbackResponse resultSpecificFeedbackResponse = SpecificFeedbackResponseMapper.toSpecificFeedbackResponse(
			surveyDto, feedbackDto);

		// then
		assertEquals(expectedSpecificFeedbackResponse, resultSpecificFeedbackResponse);
	}

	private static SurveyDto getSurveyDto() {
		return SurveyDto.builder()
			.id(1L)
			.formQuestionDtoableList(Arrays.asList(ChoiceFormQuestionDto.builder()
				.id(3L)
				.questionDtoType(QuestionDtoType.CHOICE)
				.title("Choice Question")
				.order(1)
				.maxSelectionCount(2)
				.choiceDtoList(Arrays.asList(ChoiceDto.builder().id(1L).content("Choice 1").order(1).build(),
					ChoiceDto.builder().id(2L).content("Choice 2").order(2).build()))
				.build(),
				ShortFormQuestionDto.builder()
				.id(4L)
				.questionDtoType(QuestionDtoType.SHORT)
				.title("Short Question")
				.order(2)
				.build()))
			.build();
	}

	private static FeedbackDto getFeedbackDto() {
		Set<Long> choiceIdSet = new HashSet<>();
		choiceIdSet.add(1L);
		choiceIdSet.add(2L);
		return FeedbackDto.builder()
			.id(7L)
			.surveyId(1L)
			.formQuestionFeedbackDtoableList(
				List.of(ChoiceFormQuestionFeedbackDto.builder()
						.id(1L)
						.questionId(3L)
						.isRead(true)
						.selectedChoiceIdSet(choiceIdSet)
						.build(),
					ShortFormQuestionFeedbackDto.builder()
						.id(2L)
						.questionId(4L)
						.isRead(true)
						.replyList(List.of("reply1", "reply2"))
						.build()))
			.isRead(true)
			.reviewerDto(ReviewerDto.builder()
				.id(34L)
				.nickName("sujin")
				.collaborationExperience(true)
				.position("developer")
				.build())
			.createdAt(LocalDateTime.of(2023, 5, 26, 6, 46, 0))
			.updatedAt(LocalDateTime.of(2023, 5, 26, 6, 46, 0))
			.build();
	}

	private static SpecificFeedbackResponse getSpecificFeedbackResponse() {
		return SpecificFeedbackResponse.builder()
			.feedbackId(7L)
			.createdAt(LocalDateTime.of(2023, 5, 26, 6, 46, 0))
			.reviewer(ReviewerResponse.builder()
				.nickName("sujin").collaborationExperience(true).position("developer").build())
			.question(List.of(
				ChoiceFormFeedbackResponse.builder()
					.questionId(3L)
					.type("choice")
					.title("Choice Question")
					.order(1)
					.isRead(true)
					.choices(List.of(
						ChoiceResponse.builder()
							.choiceId(1L)
							.content("Choice 1")
							.order(1)
							.build(),
						ChoiceResponse.builder()
							.choiceId(2L)
							.content("Choice 2")
							.order(2)
							.build()
					))
					.build(),
				ShortFormFeedbackResponse.builder()
					.questionId(4L)
					.type("short")
					.title("Short Question")
					.order(2)
					.isRead(true)
					.reply(
						List.of("reply1", "reply2")
					)
					.build()
			))
			.build();
	}
}