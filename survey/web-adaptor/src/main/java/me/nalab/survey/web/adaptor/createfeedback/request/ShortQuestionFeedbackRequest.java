package me.nalab.survey.web.adaptor.createfeedback.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ShortQuestionFeedbackRequest extends AbstractQuestionFeedbackRequest {

	@JsonProperty("reply")
	private List<String> replyList;

}
