package me.nalab.survey.application.common.feedback.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class ReviewerDto {

	private Long id;
	private final String nickName;
	private final boolean collaborationExperience;
	private final String position;

}
