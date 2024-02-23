package me.nalab.survey.web.adaptor.bookmark.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class SurveyBookmarkResponse {

    @JsonProperty("target_id")
    private final String targetId;
    @JsonProperty("survey_id")
    private final String surveyId;
    private final String nickname;
    private final String position;
    private final String job;
    @JsonProperty("image_url")
    private final String imageUrl;

}
