package me.nalab.survey.web.adaptor.feedbackreviewers.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class FeedbackReviewer {

	@JsonProperty("feedback_id")
	private String feedbackId;

	@JsonProperty("created_at")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime createdAt;

	private ReviewerResponse reviewer;

	@JsonProperty("is_read")
	private Boolean isRead;
}
