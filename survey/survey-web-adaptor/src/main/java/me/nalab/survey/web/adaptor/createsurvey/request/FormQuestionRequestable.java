package me.nalab.survey.web.adaptor.createsurvey.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.nalab.survey.application.common.survey.dto.QuestionDtoType;

@Getter
@ToString
@EqualsAndHashCode
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
	@JsonSubTypes.Type(value = ShortFormQuestionRequest.class, name = "short"),
	@JsonSubTypes.Type(value = ChoiceFormQuestionRequest.class, name = "choice"),
})
public abstract class FormQuestionRequestable {

	@NotBlank(message = "질문지의 title 은 공백이 되면 안됩니다.")
	@Size(min = 1, max = 45, message = "질문지 title의 길이는 1 에서 45 사이가 되어야 합니다.")
	protected String title;

	@Min(value = 1, message = "질문지의 order 는 1보다 작아지면 안됩니다.")
	@Max(value = 24, message = "질문지의 order 는 24보다 커지면 안됩니다.")
	protected Integer order;

	public abstract QuestionDtoType getQuestionFormType();

}
