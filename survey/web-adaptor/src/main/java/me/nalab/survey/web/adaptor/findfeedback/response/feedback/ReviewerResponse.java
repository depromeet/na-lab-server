package me.nalab.survey.web.adaptor.findfeedback.response.feedback;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ReviewerResponse {

	private final Long id;
	@JsonProperty("nickname")
	private final String nickName;
	@JsonProperty("collaboration_experience")
	private final boolean collaborationExperience;
	private final String position;

}
