package me.nalab.survey.domain.survey;

import lombok.Builder;
import lombok.Getter;
import me.nalab.survey.domain.survey.api.feedback.ChoiceFeedbackable;

@Builder
@Getter
public class Choice implements ChoiceFeedbackable {

	private final Long id;
	private final String content;

}
