package me.nalab.survey.web.adaptor.summaryreviewer.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class ReviewerSummaryResponse {

	@JsonProperty("collaboration_experience")
	private final CollaborationSummaryResponse collaborationSummaryResponse;
	@JsonProperty("position")
	private final PositionSummaryReponse positionSummaryReponse;

}
