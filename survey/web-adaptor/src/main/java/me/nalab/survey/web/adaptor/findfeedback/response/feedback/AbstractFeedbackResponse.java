package me.nalab.survey.web.adaptor.findfeedback.response.feedback;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@SuperBuilder
public class AbstractFeedbackResponse {

	private final Long id;
	@JsonProperty("created_at")
	private final LocalDateTime createdAt;
	@JsonProperty("is_read")
	private final boolean read;
	@JsonProperty("reviewer")
	private final ReviewerResponse reviewerResponse;

}
