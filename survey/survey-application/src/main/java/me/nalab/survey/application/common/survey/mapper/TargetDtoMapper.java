package me.nalab.survey.application.common.survey.mapper;

import me.nalab.survey.application.common.survey.dto.TargetDto;
import me.nalab.survey.domain.target.SurveyBookmark;
import me.nalab.survey.domain.target.Target;

public class TargetDtoMapper {

	private TargetDtoMapper() {
		throw new UnsupportedOperationException("Cannot invoke constructor \"TargetDtoMapper()\"");
	}

	public static Target toTarget(TargetDto targetDto) {
		return Target.builder()
			.id(targetDto.getId())
			.nickname(targetDto.getNickname())
			.build();
	}

	public static TargetDto toTargetDto(Target target) {
		return TargetDto.builder()
			.id(target.getId())
			.nickname(target.getNickname())
			.position(target.getPosition())
			.bookmarkedSurveys(target.getBookmarkedSurveys().stream()
				.map(SurveyBookmark::surveyId)
				.toList())
			.build();
	}
}
