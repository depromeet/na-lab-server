package me.nalab.survey.domain.survey;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public abstract class FormQuestionable {

	protected final Long id;
	protected final String title;
	protected final LocalDateTime createdAt;
	protected final LocalDateTime updatedAt;
	protected final Integer order;
	protected final QuestionType questionType;

}
