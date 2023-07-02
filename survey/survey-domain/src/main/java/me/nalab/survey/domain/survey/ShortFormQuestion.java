package me.nalab.survey.domain.survey;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import me.nalab.survey.domain.survey.spi.QuestionFeedbackValidable;

@SuperBuilder
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShortFormQuestion extends FormQuestionable {

	private final ShortFormQuestionType shortFormQuestionType;

	@Override
	void throwIfIsNotValidQuestionFeedback(QuestionFeedbackValidable questionFeedbackable) {
		questionFeedbackable.isValidQuestionFeedback(this);
	}

}
