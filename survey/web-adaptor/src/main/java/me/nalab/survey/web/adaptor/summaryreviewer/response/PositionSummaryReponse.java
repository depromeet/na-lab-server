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
	@JsonProperty("product-manager")
	private final int productManager;
	private final int programmer;
	private final int other;

}
