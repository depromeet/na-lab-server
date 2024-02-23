package me.nalab.survey.web.adaptor.bookmark;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.port.in.web.bookmark.SurveyBookmarkReplaceUseCase;
import me.nalab.survey.web.adaptor.bookmark.response.SurveyBookmarkResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class SurveyBookmarkReplaceController {

    private final SurveyBookmarkReplaceUseCase surveyBookmarkReplaceUseCase;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/surveys/{survey_id}/bookmarks")
    public SurveyBookmarkResponse replaceBookmark(@RequestAttribute("logined") Long targetId,
        @PathVariable("survey_id") Long surveyId) {
        var surveyBookmarked = surveyBookmarkReplaceUseCase.flipBookmark(targetId, surveyId);

        return SurveyBookmarkResponse.builder()
            .targetId(surveyBookmarked.targetId().toString())
            .surveyId(surveyBookmarked.surveyId().toString())
            .job(surveyBookmarked.job())
            .nickname(surveyBookmarked.nickname())
            .imageUrl(surveyBookmarked.imageUrl())
            .position(surveyBookmarked.position())
            .build();
    }

}
