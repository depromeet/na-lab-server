package me.nalab.survey.application.port.in.web.findid;

import java.util.List;

/**
 * 로그인된 타겟이 생성한 surveyId를 조회하는 역할을 하는 인터페이스 입니다.
 */
public interface SurveyIdFindUseCase {

	/**
	 * TargetId에 해당하는 타겟이 생성한 Survey의 Id 리스트를 반환합니다
	 *
	 * @param targetId surveyId를 조회할 타겟의 id
	 * @return List 조회된 survey의 id
	 */
	List<Long> getSurveyIdByTargetId(Long targetId);

}
