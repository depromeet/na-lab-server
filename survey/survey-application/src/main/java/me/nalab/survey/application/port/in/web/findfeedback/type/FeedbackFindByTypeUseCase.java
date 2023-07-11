package me.nalab.survey.application.port.in.web.findfeedback.type;

import java.util.List;

import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.survey.dto.FormQuestionDtoable;

/**
 * type으로 Feedback을 찾는 UseCase 입니다.
 */
public interface FeedbackFindByTypeUseCase {

	/**
	 * surveyId와 formType을 입력받아, 해당하는 모든 formQuestionDtoable을 List 형식으로 반환합니다
	 *
	 * @param surveyId 질문폼에 해당하는 id
	 * @param formType 현재 formType은 "tendency" 만 허용
	 * @return 해당하는 모든 FormQuestionDtoable 만약, 어떠한 FormQuestionDtoable도 없다면, 빈 List를 반환합니다.
	 */
	List<FormQuestionDtoable> findFormQuestionBySurveyIdAndType(Long surveyId, String formType);

	/**
	 * surveyId를 입력으로 받아, surveyId에 해당하는 모든 FeedbackDto를 반환합니다.
	 *
	 * @param surveyId 질문폼에 해당하는 id
	 * @return 해당하는 모든 FeedbackDto 만약, 어떠한 FeedbackDto도 없다면, 빈 List를 반환합니다.
	 */
	List<FeedbackDto> findFeedbackBySurveyId(Long surveyId);

}
