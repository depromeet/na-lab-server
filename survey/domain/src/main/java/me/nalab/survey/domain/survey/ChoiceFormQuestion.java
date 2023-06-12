package me.nalab.survey.domain.survey;

import java.util.Collections;
import java.util.List;
import java.util.function.LongSupplier;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import me.nalab.survey.domain.exception.IllegalQuestionFeedbackException;
import me.nalab.survey.domain.survey.spi.QuestionFeedbackValidable;
import me.nalab.survey.domain.survey.valid.ChoiceFormQuestionValidator;

@SuperBuilder
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ChoiceFormQuestion extends FormQuestionable {

	private final List<Choice> choiceList;
	private final Integer maxSelectableCount;
	private final ChoiceFormQuestionType choiceFormQuestionType;

	ChoiceFormQuestion(ChoiceFormQuestionBuilder<?, ?> builder) {
		super(builder);
		this.choiceList = builder.choiceList;
		withSortedChoiceList(this.choiceList);
		this.maxSelectableCount = builder.maxSelectableCount;
		this.choiceFormQuestionType = builder.choiceFormQuestionType;
		ChoiceFormQuestionValidator.validSelf(this);
	}

	private void withSortedChoiceList(List<Choice> choiceList) {
		Collections.sort(choiceList);
	}

	@Override
	public void cascadeId(LongSupplier idSupplier) {
		for(Choice choice : choiceList) {
			choice.withId(idSupplier);
		}
	}

	@Override
	void throwIfIsNotValidQuestionFeedback(QuestionFeedbackValidable questionFeedbackable) {
		if(!questionFeedbackable.isValidQuestionFeedback(this)) {
			throw new IllegalQuestionFeedbackException(questionFeedbackable);
		}
	}

}
