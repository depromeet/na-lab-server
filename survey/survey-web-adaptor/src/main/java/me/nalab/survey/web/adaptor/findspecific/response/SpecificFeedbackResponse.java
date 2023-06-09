package me.nalab.survey.web.adaptor.findspecific.response;

import java.time.LocalDateTime;
import java.util.List;

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
public class SpecificFeedbackResponse {

	@JsonProperty("feedback_id")
	private final String feedbackId;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@JsonProperty("created_at")
	private final LocalDateTime createdAt;

	private final ReviewerResponse reviewer;

	private final List<FormFeedbackResponseable> question;
}
