package me.nalab.survey.domain.survey;

import lombok.Builder;
import me.nalab.survey.domain.survey.api.feedback.ShortFormQuestionFeedbackable;

@Builder
public class ShortFormQuestion extends FormQuestionable implements ShortFormQuestionFeedbackable {

	private final ShortFormQuestionType shortFormQuestionType;

}
