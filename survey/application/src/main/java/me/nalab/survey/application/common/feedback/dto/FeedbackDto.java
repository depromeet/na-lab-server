package me.nalab.survey.application.common.feedback.dto;

import java.time.LocalDateTime;
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
	private final ReviewerDto reviewer;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;

}
