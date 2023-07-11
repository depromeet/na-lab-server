package me.nalab.survey.application.port.in.web.findfeedback.type;

import java.util.List;

import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.survey.dto.FormQuestionDtoable;
import me.nalab.survey.application.common.survey.dto.SurveyDto;

/**
 * type으로 Feedback을 찾는 UseCase 입니다.
 */
public interface FeedbackFindByTypeUseCase {

	/**
	 * surveyId를 입력으로 받아, surveyId에 해당하는 SurveyDto를 반환합니다.
	 *
	 * @param surveyId survey의 id
	 * @return SurveyDto, Survey를 SurveyDto로 변환한 Dto type 을 반환합니다.
	 */
	SurveyDto findSurvey(Long surveyId);

	/**
	 * surveyId를 입력으로 받아, surveyId에 해당하는 모든 FeedbackDto를 반환합니다.
	 *
	 * @param surveyId 질문폼에 해당하는 id
	 * @return 해당하는 모든 FeedbackDto 만약, 어떠한 FeedbackDto도 없다면, 빈 List를 반환합니다.
	 */
	List<FeedbackDto> findFeedbackBySurveyId(Long surveyId);

	List<FormQuestionDtoable> formQuestionMatchingWithType(SurveyDto surveyDto, String formType);

}
