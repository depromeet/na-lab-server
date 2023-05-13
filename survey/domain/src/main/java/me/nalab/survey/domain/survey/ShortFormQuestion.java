package me.nalab.survey.domain.survey;

import lombok.Builder;

@Builder
public class ShortFormQuestion extends FormQuestionable {

	private final ShortFormQuestionType shortFormQuestionType;

}
