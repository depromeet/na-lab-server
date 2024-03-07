package me.nalab.survey.web.adaptor.bookmark;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.port.in.web.bookmark.SurveyBookmarkUseCase;
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
public class SurveyBookmarkController {

    private final SurveyBookmarkUseCase surveyBookmarkReplaceUseCase;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/surveys/{survey_id}/bookmarks")
    public void bookmark(@RequestAttribute("logined") Long targetId,
        @PathVariable("survey_id") Long surveyId) {
        surveyBookmarkReplaceUseCase.bookmark(targetId, surveyId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/surveys/{survey_id}/bookmarks/cancels")
    public void cancelBookmark(@RequestAttribute("logined") Long targetId,
        @PathVariable("survey_id") Long surveyId) {
        surveyBookmarkReplaceUseCase.cancelBookmark(targetId, surveyId);
    }
}
