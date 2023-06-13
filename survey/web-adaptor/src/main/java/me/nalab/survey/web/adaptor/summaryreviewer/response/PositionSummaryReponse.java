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
public class PositionSummaryReponse {

	private final int designer;
	@JsonProperty("pm")
	private final int productManager;
	private final int developer;
	private final int others;

}
