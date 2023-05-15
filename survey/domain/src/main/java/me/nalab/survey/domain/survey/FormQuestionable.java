package me.nalab.survey.domain.survey;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@ToString
public abstract class FormQuestionable {

	protected final Long id;
	protected final String title;
	protected final LocalDateTime createdAt;
	protected final LocalDateTime updatedAt;
	protected final Integer order;
	protected final QuestionType questionType;

	abstract FormQuestionable ofIncreaseOrder(int defaultOrderSize);

}
