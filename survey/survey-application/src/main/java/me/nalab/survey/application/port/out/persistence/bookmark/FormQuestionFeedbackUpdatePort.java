package me.nalab.survey.application.port.out.persistence.bookmark;

import me.nalab.survey.domain.feedback.FormQuestionFeedbackable;

/**
 * FormQuestionFeedback의 북마크를 교체하는 인터페이스입니다.
 */
public interface FormQuestionFeedbackUpdatePort {

	/**
	 * FormQuestionFeedbackable를 인자로받아 북마크 필드를 교체합니다.
	 *
	 * @param formQuestionFeedbackable 북마크 필드를 교체할 FormQuestionFeedbackable
	 */
	void updateFormQuestionFeedback(FormQuestionFeedbackable formQuestionFeedbackable);

}
