package me.nalab.survey.domain.survey;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public abstract class FormQuestionable {

	private final Long id;
	private final String title;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;
	private final Integer order;
	private final QuestionType questionType;

}
