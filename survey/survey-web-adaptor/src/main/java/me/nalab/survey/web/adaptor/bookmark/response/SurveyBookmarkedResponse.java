package me.nalab.survey.web.adaptor.bookmark.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SurveyBookmarkedResponse(
    @JsonProperty("survey_id")
    String surveyId
) {

    public static SurveyBookmarkedResponse of(Long surveyId) {
        return new SurveyBookmarkedResponse(surveyId.toString());
    }
}
