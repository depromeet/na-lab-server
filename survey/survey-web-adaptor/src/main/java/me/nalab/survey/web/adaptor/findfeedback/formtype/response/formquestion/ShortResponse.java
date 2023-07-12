package me.nalab.survey.web.adaptor.findfeedback.formtype.response.formquestion;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ShortResponse {

	@JsonProperty("feedback_id")
	private final String feedbackId;

	@JsonProperty("created_at")
	private final LocalDateTime createdAt;

	@JsonProperty("reply")
	List<String> replyList;

}
