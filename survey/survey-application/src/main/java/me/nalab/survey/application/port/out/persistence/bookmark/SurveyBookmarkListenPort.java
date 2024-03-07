package me.nalab.survey.application.port.out.persistence.bookmark;

public interface SurveyBookmarkListenPort {

    void increaseBookmarked(Long targetId);

    void decreaseBookmarked(Long targetId);
}
