package me.nalab.survey.application.port.in.web;

/**
 * 가장 최근에 생성된 Survey의 ID를 조회하는 usecase 입니다.
 */
public interface LatestSurveyIdFindUseCase {

	/**
	 * targetId를 인자로 받고, targetId에 해당하는 타겟이 가장 최근에 생성한 Survey의 id를 반환합니다.
	 *
	 * @param targetId Survey를 조회할 타겟의 id
	 * @return Long survey의 id
	 */
	Long getLatestCreatedSurveyId(Long targetId);

}
