package me.nalab.survey.domain.feedback;

import java.util.Set;
import java.util.stream.Collectors;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import me.nalab.survey.domain.survey.Choice;
import me.nalab.survey.domain.survey.ChoiceFormQuestion;
import me.nalab.survey.domain.survey.FormQuestionable;

@SuperBuilder
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ChoiceFormQuestionFeedback extends FormQuestionFeedbackable {

	private final Set<Long> selectedChoiceIdSet;

	@Override
	public boolean isValidQuestionFeedback(FormQuestionable formQuestionable) {
		if(formQuestionable instanceof ChoiceFormQuestion) {
			ChoiceFormQuestion choiceFormQuestion = (ChoiceFormQuestion)formQuestionable;
			return isSelectCountDoesNotOverflow(choiceFormQuestion) && isSelectedIdMissMatched(choiceFormQuestion);
		}
		return false;
	}

	private boolean isSelectCountDoesNotOverflow(ChoiceFormQuestion choiceFormQuestion) {
		return choiceFormQuestion.getMaxSelectionCount() >= selectedChoiceIdSet.size();
	}

	private boolean isSelectedIdMissMatched(ChoiceFormQuestion choiceFormQuestion) {
		Set<Long> choiceableIdSet = choiceFormQuestion.getChoiceList()
			.stream()
			.map(Choice::getId)
			.collect(Collectors.toSet());
		
		for(Long selected : selectedChoiceIdSet) {
			if(choiceableIdSet.contains(selected)) {
				continue;
			}
			return false;
		}
		return true;
	}

}
