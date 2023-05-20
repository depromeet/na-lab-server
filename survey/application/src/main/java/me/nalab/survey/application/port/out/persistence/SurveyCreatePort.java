package me.nalab.survey.application.port.out.persistence;

import me.nalab.survey.domain.survey.Survey;

/**
 * 생성된 Survey를 저장하는 역할을 하는 인터페이스
 * <br>
 * 이 인터페이스의 구현체는 Survey 도메인을 저장해야함.
 */
public interface SurveyCreatePort {

	/**
	 * 이 메소드는 Survey를 저장함.
	 * @param survey 저장할 Survey의 정보
	 * @param targetId Survey를 생성한 Target의 id
	 */
	void createSurvey(Long targetId, Survey survey);

}
