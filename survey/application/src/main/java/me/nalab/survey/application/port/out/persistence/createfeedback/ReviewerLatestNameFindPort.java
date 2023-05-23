package me.nalab.survey.application.port.out.persistence.createfeedback;

import java.util.Optional;

/**
 * 마지막으로 리뷰를 작성한 리뷰어의 이름을 찾는 인터페이스 입니다.
 */
public interface ReviewerLatestNameFindPort {

	/**
	 * 마지막으로 리뷰를한 리뷰어의 nickname을 조회합니다.
	 *
	 * @param surveyId 리뷰어의 정보를 찾을 survey의 id입니다.
	 * @return Optional 마지막으로 리뷰한 리뷰어의 닉네임 입니다. 없다면, Optional.empty를 반환해야합니다.
	 */
	Optional<String> getLatestReviewerNameBySurveyId(Long surveyId);

}
