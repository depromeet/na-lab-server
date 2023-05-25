package me.nalab.survey.web.adaptor.feedbacksummary.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class FeedbackSummaryResponse {

	@JsonProperty("all_feedback_count")
	private int allFeedbackCount;

	@JsonProperty("new_feedback_count")
	private int newFeedbackCount;
}
