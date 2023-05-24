package me.nalab.survey.web.adaptor.createfeedback.request;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ChoiceQuestionFeedbackRequest extends AbstractQuestionFeedbackRequest {

	@JsonProperty("choies")
	private Set<Long> choiceSet;

}
