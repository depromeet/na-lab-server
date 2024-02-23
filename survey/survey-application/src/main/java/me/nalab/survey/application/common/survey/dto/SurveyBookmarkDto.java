package me.nalab.survey.application.common.survey.dto;

import lombok.Builder;

@Builder
public record SurveyBookmarkDto(
    Long targetId,
    Long surveyId,
    String nickname,
    String position,
    String job,
    String imageUrl
) {

}
