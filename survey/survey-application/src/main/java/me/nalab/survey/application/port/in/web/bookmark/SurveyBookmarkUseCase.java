package me.nalab.survey.application.port.in.web.bookmark;

public interface SurveyBookmarkUseCase {

    /**
     * targetId에 해당하는 유저에게 survey를 북마크합니다.
     * 이미 북마크되어있다면 북마크를 취소합니다.
     */
    void bookmark(Long targetId, Long surveyId);

}
