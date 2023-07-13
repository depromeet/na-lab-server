package me.nalab.survey.web.adaptor.findspecific.response;

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
	private final String nickName;

	@JsonProperty("collaboration_experience")
	private final boolean collaborationExperience;

	private final String position;
}
