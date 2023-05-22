package me.nalab.survey.application.common.feedback.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@ToString
@EqualsAndHashCode
public abstract class FormQuestionFeedbackDtoable {

	private Long id;
	private Long questionId;
	private boolean isRead;

}
