package me.nalab.survey.application.port.out.persistence.bookmark;

import me.nalab.survey.application.common.survey.dto.TargetDto;

public interface SurveyBookmarkListenPort {

    void listenBookmarked(TargetDto target);
}
