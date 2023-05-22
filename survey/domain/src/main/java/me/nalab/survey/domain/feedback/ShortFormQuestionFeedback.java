package me.nalab.survey.domain.feedback;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import me.nalab.survey.domain.survey.FormQuestionable;
import me.nalab.survey.domain.survey.ShortFormQuestion;

@SuperBuilder
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShortFormQuestionFeedback extends FormQuestionFeedbackable {

	private List<String> replyList;

	@Override
	public boolean isValidQuestionFeedback(FormQuestionable formQuestionable) {
		return formQuestionable instanceof ShortFormQuestion;
	}

}
