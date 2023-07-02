package me.nalab.survey.web.adaptor.createfeedback.request;

import java.util.List;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ShortQuestionFeedbackRequest extends AbstractQuestionFeedbackRequest {

	@Size(min = 1, max = 50, message = "응답은 최소 1번, 최대 50번까지만 할 수 있습니다.")
	@JsonProperty("reply")
	private List<String> replyList;

}
