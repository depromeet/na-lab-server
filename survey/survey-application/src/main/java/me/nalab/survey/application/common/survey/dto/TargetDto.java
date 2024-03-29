package me.nalab.survey.application.common.survey.dto;

import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class TargetDto {

	private final Long id;
	private final String nickname;
	private final String position;
	private final List<Long> bookmarkedSurveys;
}
