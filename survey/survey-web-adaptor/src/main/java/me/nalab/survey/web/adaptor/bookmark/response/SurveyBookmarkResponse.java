package me.nalab.survey.web.adaptor.bookmark.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

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

}
