package me.nalab.survey.application.port.in.web.bookmark;

import me.nalab.survey.application.common.survey.dto.SurveyBookmarkDto;

public interface SurveyBookmarkReplaceUseCase {

    /**
     * targetId에 해당하는 유저에게 survey를 북마크합니다.
     * 이미 북마크되어있다면 북마크를 취소합니다.
     */
    SurveyBookmarkDto bookmark(Long targetId, Long surveyId);

}
