package me.nalab.survey.application.common.survey.dto;

import java.time.Instant;
import java.util.List;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class SurveyDto {

	private final Long id;
	private final Long targetId;
	private final Instant createdAt;
	private final Instant updatedAt;
	private final List<FormQuestionDtoable> formQuestionDtoableList;

}
