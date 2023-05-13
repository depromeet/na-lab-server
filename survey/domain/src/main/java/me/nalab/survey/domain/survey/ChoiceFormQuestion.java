package me.nalab.survey.domain.survey;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import me.nalab.survey.domain.survey.api.feedback.ChoiceFormQuestionFeedbackable;

@Builder
@Getter
public class ChoiceFormQuestion extends FormQuestionable implements ChoiceFormQuestionFeedbackable {

	private final List<Choice> choiceList;
	private final Integer maxSelectionCount;
	private final ChoiceFormQuestionType choiceFormQuestionType;

}
