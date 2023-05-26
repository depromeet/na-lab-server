package me.nalab.survey.web.adaptor.feedbackreviewers.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class ReviewerResponse {

	@JsonProperty("nickname")
	private String nickName;

	@JsonProperty("collaboration_experience")
	private Boolean collaborationExperience;

	private String position;
}
