package me.nalab.survey.web.adaptor.bookmark;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.port.in.web.bookmark.BookmarkedSurveyFindUseCase;
import me.nalab.survey.web.adaptor.bookmark.response.SurveyBookmarksResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class BookmarkedSurveyFindController {

    private final BookmarkedSurveyFindUseCase bookmarkedSurveyFindUseCase;

    @GetMapping("/surveys/bookmarks")
    @ResponseStatus(HttpStatus.OK)
    public SurveyBookmarksResponse findBookmarkedSurveys(
        @RequestAttribute("logined") Long loginedTargetId,
        @RequestParam(value = "last-survey-id", defaultValue = "0") Long lastSurveyId,
        @RequestParam(value = "count", defaultValue = "20") Integer count
    ) {
        var bookmarkedSurveys = bookmarkedSurveyFindUseCase.findBookmarkedSurveysByTargetId(loginedTargetId,
            lastSurveyId, count);
        return SurveyBookmarksResponse.of(bookmarkedSurveys);
    }

}
