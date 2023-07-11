package me.nalab.survey.application.port.out.persistence.findfeedback.type;

import java.util.List;

import me.nalab.survey.domain.survey.FormQuestionable;

/**
 * FormQuestionable을 찾는 인터페이스입니다.
 */
public interface FormQuestionFindPort {

	/**
	 *
	 * @param surveyId surveyId 질문폼에 해당하는 id
	 * @param formType 현재 formType은 "tendency" 만 허용
	 * @return 해당하는 모든 FormQuestionable 만약, 어떠한 FormQuestionable도 없다면, 빈 List를 반환합니다.
	 */
	List<FormQuestionable> findFormQuestionableBySurveyIdAndType(Long surveyId, String formType);

}
