package me.nalab.survey.application.port.in.web.findfeedback.type;

import java.util.List;

import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.survey.dto.FormQuestionDtoable;

/**
 * type으로 Feedback을 찾는 UseCase 입니다.
 */
public interface FeedbackFindByTypeUseCase {

	/**
	 * surveyId를 입력으로 받아, surveyId에 해당하는 모든 FeedbackDto를 반환합니다.
	 *
	 * @param surveyId 질문폼에 해당하는 id
	 * @return 해당하는 모든 FeedbackDto 만약, 어떠한 FeedbackDto도 없다면, 빈 List를 반환합니다.
	 */
	List<FeedbackDto> findFeedbackBySurveyId(Long surveyId);

	/**
	 *
	 * @param surveyId survey의 id
	 * @param formType
	 * @return 질문 폼의 질문 중 formType에 해당하는 FormQuestionDtoable을 List의 형태로 반환합니다.
	 */
	List<FormQuestionDtoable> formQuestionMatchingWithType(Long surveyId, String formType);

}
