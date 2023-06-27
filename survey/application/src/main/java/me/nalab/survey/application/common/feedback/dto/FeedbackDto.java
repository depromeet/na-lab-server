package me.nalab.survey.application.common.feedback.dto;

import java.time.Instant;
import java.util.List;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class FeedbackDto {

	private Long id;
	private Long surveyId;
	private final List<FormQuestionFeedbackDtoable> formQuestionFeedbackDtoableList;
	private final boolean isRead;
	private final ReviewerDto reviewerDto;
	private final Instant createdAt;
	private final Instant updatedAt;

}
