package me.nalab.survey.application.port.in.web.bookmark;

public interface SurveyBookmarkUseCase {

    /**
     * targetId에 해당하는 유저에게 survey를 북마크합니다.
     */
    void bookmark(Long targetId, Long surveyId);

    /**
     * targetId에 해당하는 유저에게 survey를 북마크 취소합니다. 북마크 되어있지 않다면, 아무동작도 하지 않습니다.
     */
    void cancelBookmark(Long targetId, Long surveyId);

}
