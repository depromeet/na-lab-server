package me.nalab.survey.domain.survey;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class ShortFormQuestion extends FormQuestionable {

	private final ShortFormQuestionType shortFormQuestionType;

}
