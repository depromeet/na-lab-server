package me.nalab.survey.domain.survey;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import me.nalab.survey.domain.survey.api.feedback.Feedbackable;

@Getter
@Builder
public class Survey implements Feedbackable {

	private final Long id;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;
	private final List<FormQuestionable> formQuestionableList;

}
