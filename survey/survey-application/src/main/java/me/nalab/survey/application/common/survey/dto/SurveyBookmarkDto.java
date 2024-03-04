package me.nalab.survey.application.common.survey.dto;

import lombok.Builder;
import me.nalab.survey.domain.target.SurveyBookmark;
import me.nalab.survey.domain.target.Target;

@Builder
public record SurveyBookmarkDto(
    Long targetId,
    Long surveyId,
    String nickname,
    String position,
    String job,
    String imageUrl
) {

    public static SurveyBookmarkDto from(Long surveyId, Target target) {
        return SurveyBookmarkDto.builder()
            .surveyId(surveyId)
            .targetId(target.getId())
            .nickname(target.getNickname())
            .job(target.getJob())
            .imageUrl(target.getImageUrl())
            .position(target.getPosition())
            .build();
    }

}
