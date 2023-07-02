package me.nalab.survey.web.adaptor.createsurvey.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class ChoiceRequest {

	@NotBlank(message = "객관식 선택지의 content 는 공백이 되면 안됩니다.")
	@Size(min = 1, max = 18, message = "객관식 선택지의 content 의 길이는 1에서 18사이가 되어야 합니다.")
	private String content;

	@Min(value = 1, message = "객관식 선택지의 order 는 1보다 작아지면 안됩니다.")
	@Max(value = 20, message = "객관식 선택지의 order 는 20보다 커지면 안됩니다.")
	private Integer order;

}
