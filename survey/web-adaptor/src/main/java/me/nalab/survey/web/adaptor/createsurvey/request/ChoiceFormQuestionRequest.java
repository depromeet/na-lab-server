package me.nalab.survey.web.adaptor.createsurvey.request;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.nalab.survey.application.common.survey.dto.QuestionDtoType;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ChoiceFormQuestionRequest extends FormQuestionRequestable {

	@Min(value = 1, message = "객관식 질문지의 최대 선택가능한 수는 1보다 작아지면 안됩니다.")
	@JsonProperty("max_selectable_count")
	private Integer maxSelectableCount;

	@JsonProperty("form_type")
	private String choiceFormQuestionType;

	@Size(min = 2, max = 20, message = "객관식 질문의 수는 2 에서 20 사이가 되어야 합니다.")
	@JsonProperty("choices")
	private List<ChoiceRequest> choiceRequestList;

	@Override
	public QuestionDtoType getQuestionFormType() {
		return QuestionDtoType.CHOICE;
	}

}
