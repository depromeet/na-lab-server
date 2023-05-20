package me.nalab.survey.web.adaptor.createsurvey.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.nalab.survey.application.common.dto.QuestionDtoType;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ShortFormQuestionRequest extends FormQuestionRequestable {

	@JsonProperty("form_type")
	private String shortFormQuestionType;

	@Override
	public QuestionDtoType getQuestionFormType() {
		return QuestionDtoType.SHORT;
	}

}