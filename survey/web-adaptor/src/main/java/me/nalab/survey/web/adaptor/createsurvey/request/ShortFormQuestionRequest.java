package me.nalab.survey.web.adaptor.createsurvey.request;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.nalab.survey.application.common.survey.dto.QuestionDtoType;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ShortFormQuestionRequest extends FormQuestionRequestable {

	@JsonProperty("form_type")
	@Pattern(regexp = "^(strength|custom)$", message = "객관식 질문 type은 strength 또는 custom 중 하나이어야 합니다.")
	private String shortFormQuestionType;

	@Override
	public QuestionDtoType getQuestionFormType() {
		return QuestionDtoType.SHORT;
	}

}
