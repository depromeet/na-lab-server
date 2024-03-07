package me.nalab.survey.application.port.in.web.bookmark;

import java.util.List;
import me.nalab.survey.application.common.survey.dto.SurveyBookmarkDto;

public interface BookmarkedSurveyFindUseCase {

    /**
     * targetId를 입력받아 SurveyBookmarkDto를 반환합니다.
     */
    List<SurveyBookmarkDto> findBookmarkedSurveysByTargetId(Long targetId, Long lastSurveyId, Integer count);

}
