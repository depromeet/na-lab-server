package me.nalab.survey.application.port.in.web.findfeedback;

import me.nalab.survey.application.common.feedback.dto.FeedbackDto;

import java.util.List;

/**
 * 북마크된 피드백을 조회. 북마크된 피드백은 전체 응답이 아닌 북마크된 응답만 가지고 있습니다.
 */
public interface BookmarkedFeedbackFindUseCase {

    /**
     * 특정 survey의 피드백 중 북마크된 피드백만 조회합니다.
     *
     * @param surveyId 조회하고자 하는 Feedback이 속한 Survey의 식별자
     * @return 북마크된 모든 피드백들
     */
    List<FeedbackDto> findAllBySurveyId(Long surveyId);

}
