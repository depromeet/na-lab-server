package me.nalab.survey.application.port.out.persistence.summaryreviewer;

import java.util.List;

import me.nalab.survey.domain.feedback.Reviewer;

/**
 * SurveyId에 해당하는 모든 저장된 Reviewer 를 찾는 port 입니다.
 */
public interface ReviewerFindPort {

	/**
	 * surveyId를 인자로 받아, 응답한 모든 Reviewer 를 조회합니다.
	 * 만약, 어떠한 Reviewer 도 찾을 수 없다면, 빈 배열을 반환합니다.
	 *
	 * @param surveyId Reviewer 를 조회할 survey 의 id
	 * @return List 응답한 모든 Reviewer
	 */
	List<Reviewer> findAllReviewer(Long surveyId);

}
