package me.nalab.survey.web.adaptor.createfeedback.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
	@JsonSubTypes.Type(value = ShortQuestionFeedbackRequest.class, name = "short"),
	@JsonSubTypes.Type(value = ChoiceQuestionFeedbackRequest.class, name = "choice"),
})
public abstract class AbstractQuestionFeedbackRequest {

	@JsonProperty("question_id")
	protected String questionId;

}
