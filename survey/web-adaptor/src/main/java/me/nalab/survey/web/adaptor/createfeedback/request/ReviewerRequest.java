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

	@Pattern(regexp = "^(designer|pm|developer|others)$")
	private String position;

}
