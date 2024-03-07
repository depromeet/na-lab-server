package me.nalab.survey.web.adaptor.bookmark.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import me.nalab.survey.application.common.survey.dto.SurveyBookmarkDto;

public record SurveyBookmarksResponse(
    @JsonProperty("bookmarked_surveys")
    List<SurveyBookmarkResponse> bookmarkedSurveys
) {

    public static SurveyBookmarksResponse of(List<SurveyBookmarkDto> surveyBookmarkDtos) {
        return new SurveyBookmarksResponse(
            surveyBookmarkDtos.stream()
                .map(SurveyBookmarkResponse::of)
                .toList()
        );
    }

}
