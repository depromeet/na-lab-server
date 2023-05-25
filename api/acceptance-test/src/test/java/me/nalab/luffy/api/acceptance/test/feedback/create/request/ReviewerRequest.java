package me.nalab.luffy.api.acceptance.test.feedback.create.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewerRequest {

	@JsonProperty("collaboration_experience")
	private boolean collaborationExperience;
	private String position;

}
