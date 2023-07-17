package me.nalab.survey.application.port.in.web.existsurvey;

/**
 * Survey의 존재 유무를 확인하는 인터페이스 입니다.
 */
public interface SurveyExistUseCase {

	/**
	 * targetId를 받아, survey의 존재유무를 반환합니다.
	 *
	 * @param targetId survey를 확인할 target의 id
	 * @return boolean 존재한다면 true, 없다면 false
	 */
	boolean existSurveyByTargetId(Long targetId);

}
