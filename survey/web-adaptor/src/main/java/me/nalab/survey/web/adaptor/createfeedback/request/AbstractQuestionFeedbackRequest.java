package me.nalab.survey.web.adaptor.createfeedback.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import me.nalab.survey.web.adaptor.createsurvey.request.ChoiceFormQuestionRequest;
import me.nalab.survey.web.adaptor.createsurvey.request.ShortFormQuestionRequest;

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
	protected Long questionId;

}
