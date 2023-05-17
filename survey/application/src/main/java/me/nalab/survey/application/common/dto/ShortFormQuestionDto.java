package me.nalab.survey.application.common.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ShortFormQuestionDto extends FormQuestionDtoable {

	private final ShortFormQuestionDtoType shortFormQuestionDtoType;

}
