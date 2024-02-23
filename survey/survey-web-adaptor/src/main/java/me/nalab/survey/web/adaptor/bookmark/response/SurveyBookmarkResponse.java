package me.nalab.survey.web.adaptor.bookmark.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import me.nalab.survey.application.common.survey.dto.SurveyBookmarkDto;

@Builder
public record SurveyBookmarkResponse(
    @JsonProperty("target_id")
    String targetId,
    @JsonProperty("survey_id")
    String surveyId,
    String nickname,
    String position,
    String job,
    @JsonProperty("image_url")
    String imageUrl
) {

    public static SurveyBookmarkResponse of(SurveyBookmarkDto surveyBookmarkDto) {
        return SurveyBookmarkResponse.builder()
            .targetId(surveyBookmarkDto.targetId().toString())
            .surveyId(surveyBookmarkDto.surveyId().toString())
            .job(surveyBookmarkDto.job())
            .nickname(surveyBookmarkDto.nickname())
            .imageUrl(surveyBookmarkDto.imageUrl())
            .position(surveyBookmarkDto.position())
            .build();
    }
}
