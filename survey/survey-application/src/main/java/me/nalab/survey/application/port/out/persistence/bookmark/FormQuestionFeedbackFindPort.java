package me.nalab.survey.application.port.out.persistence.bookmark;

import java.util.Optional;

import me.nalab.survey.domain.feedback.FormQuestionFeedbackable;

/**
 * FormQuestionFeedbackable을 조회하는 인터페이스 입니다.
 */
public interface FormQuestionFeedbackFindPort {

	/**
	 * formQuestionFeedbackId를 인자로받아 id에 해당하는 FormQuestionFeedbackable을 반환합니다.
	 *
	 * @param formQuestionFeedbackId FormQuestionFeedbackable의 id
	 * @return Optioonal 만약, 어떠한 formQuestionFeedbackable이 없을 경우, Optional.empty()를 반환해야 합니다.
	 */
	Optional<FormQuestionFeedbackable> findFormQuestionFeedbackById(Long formQuestionFeedbackId);

}
