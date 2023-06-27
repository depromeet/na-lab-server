package me.nalab.survey.application.common.survey.dto;

import java.time.Instant;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@ToString
@EqualsAndHashCode
public abstract class FormQuestionDtoable {

	protected final Long id;
	protected final String title;
	protected final Instant createdAt;
	protected final Instant updatedAt;
	protected final Integer order;
	protected final QuestionDtoType questionDtoType;

}
