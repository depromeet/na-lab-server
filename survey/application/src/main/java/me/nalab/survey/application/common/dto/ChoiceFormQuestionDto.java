package me.nalab.survey.application.common.dto;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ChoiceFormQuestionDto extends FormQuestionDtoable {

	private final List<ChoiceDto> choiceDtoList;
	private final Integer maxSelectionCount;
	private final ChoiceFormQuestionDtoType choiceFormQuestionDtoType;

}
