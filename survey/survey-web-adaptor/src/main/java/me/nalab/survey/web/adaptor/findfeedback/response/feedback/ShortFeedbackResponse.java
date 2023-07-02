package me.nalab.survey.web.adaptor.findfeedback.response.feedback;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@SuperBuilder
public class ShortFeedbackResponse extends AbstractFeedbackResponse{

	@JsonProperty("reply")
	List<String> replyList;

}
