package me.nalab.survey.web.adaptor.findfeedback.formtype.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import me.nalab.survey.web.adaptor.findfeedback.formtype.response.formquestion.AbstractFormQuestionResponse;

@Getter
@ToString
@SuperBuilder
public class QuestionFeedbackResponse {

	@JsonProperty("question_feedback")
	private List<AbstractFormQuestionResponse> abstractFormQuestionResponseList;

}
