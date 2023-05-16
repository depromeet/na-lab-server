package me.nalab.survey.application.common.dto;

import java.time.LocalDateTime;

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
	protected final LocalDateTime createdAt;
	protected final LocalDateTime updatedAt;
	protected final Integer order;
	protected final QuestionDtoType questionDtoType;

}
