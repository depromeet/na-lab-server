package me.nalab.survey.web.adaptor.createfeedback.request;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class ReviewerRequest {

	@JsonProperty("collaboration_experience")
	private boolean collaborationExperience;

	@Pattern(regexp = "^(designer|pm|developer|others)$", message = "position은 designer, pm, developer, others 중 하나이어야 합니다.")
	private String position;

}
