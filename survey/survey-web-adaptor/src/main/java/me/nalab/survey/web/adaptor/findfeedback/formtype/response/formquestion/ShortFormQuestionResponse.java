package me.nalab.survey.web.adaptor.findfeedback.formtype.response.formquestion;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@SuperBuilder
public class ShortFormQuestionResponse extends AbstractFormQuestionResponse {

	@JsonProperty("feedbacks")
	private final List<ShortResponse> shortResponseList;

}
