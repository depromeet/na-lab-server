package me.nalab.survey.web.adaptor.summaryreviewer.response;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class CollaborationSummaryResponse {

	private final int yes;
	private final int no;

}
