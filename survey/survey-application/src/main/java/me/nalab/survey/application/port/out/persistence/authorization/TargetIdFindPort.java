package me.nalab.survey.application.port.out.persistence.authorization;

import java.util.Optional;

/**
 * Target의 id를 반환하는 인터페이스 입니다.
 */
public interface TargetIdFindPort {

	/**
	 * surveyId 를 인자로 받아, 해당 surveyId를 소유하고있는 target 의 Id를 반환합니다.
	 * 만약, target 의 Id를 찾을 수 없다면, Optional.empty() 를 반환합니다.
	 *
	 * @param surveyId survey 의 id
	 * @return 있다면, surveyId를 소유하고있는 target 의 Id
	 */
	Optional<Long> findTargetIdBySurveyId(Long surveyId);

	/**
	 * feedbackId를 인자로 받아, 해당 surveyId를 소유하고있는 target 의 Id를 반환합니다.
	 * 만약, feedback의 Id를 찾을 수 없다면, Optional.empty() 를 반환합니다.
	 *
	 * @param feedbackId feedback 의 id
	 * @return 있다면, feedbackId 를 소유하고있는 target 의 Id
	 */
	Optional<Long> findTargetIdByFeedbackId(Long feedbackId);

	/**
	 * formQuestionFeedbackId 를 인자로 받아, 해당 surveyId를 소유하고있는 target 의 id를 반환합니다.
	 * 만약, formQuestionFeedbackId 를 찾을 수 없다면, Optional.empty() 를 반환합니다.
	 *
	 * @param formQuestionFeedbackId formQuestionFeedback 의 id
	 * @return formQuestionFeedbackId를 소유하고있는 target 의 id
	 */
	Optional<Long> findTargetIdByFormQuestionFeedbackId(Long formQuestionFeedbackId);

}
