package me.nalab.survey.web.adaptor.summaryreviewer.response;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@ToString
@EqualsAndHashCode
public class CollaborationSummaryResponse {

	private final int yes;
	private final int no;

}
