package me.nalab.survey.domain.survey;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ChoiceFormQuestion extends FormQuestionable {

	private final List<Choice> choiceList;
	private final Integer maxSelectionCount;
	private final ChoiceFormQuestionType choiceFormQuestionType;

}
