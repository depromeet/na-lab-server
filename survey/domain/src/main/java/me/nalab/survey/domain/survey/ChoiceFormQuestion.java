package me.nalab.survey.domain.survey;

import java.util.Collections;
import java.util.List;
import java.util.function.LongSupplier;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import me.nalab.survey.domain.survey.valid.ChoiceFormQuestionValidator;

@SuperBuilder
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ChoiceFormQuestion extends FormQuestionable {

	private final List<Choice> choiceList;
	private final Integer maxSelectionCount;
	private final ChoiceFormQuestionType choiceFormQuestionType;

	ChoiceFormQuestion(ChoiceFormQuestionBuilder<?, ?> builder) {
		super(builder);
		this.choiceList = builder.choiceList;
		withSortedChoiceList(this.choiceList);
		this.maxSelectionCount = builder.maxSelectionCount;
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

}
