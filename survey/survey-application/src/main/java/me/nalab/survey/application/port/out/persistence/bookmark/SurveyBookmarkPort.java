package me.nalab.survey.application.port.out.persistence.bookmark;

import me.nalab.survey.domain.target.Target;

public interface SurveyBookmarkPort {

    void updateBookmark(Target target);
}
