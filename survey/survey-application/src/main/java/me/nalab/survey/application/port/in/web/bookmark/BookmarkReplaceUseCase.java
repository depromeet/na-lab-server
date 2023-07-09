package me.nalab.survey.application.port.in.web.bookmark;

/**
 * 해당 formQuestionFeedback의 북마크를 교체합니다.
 *
 * 이미 북마크가 되어있는 formQuestionFeedback일 경우, 요청 시 북마크가 지워집니다.(switch 형식)
 */
public interface BookmarkReplaceUseCase {

	/**
	 * formQuestionFeedbackId를 인자로 받고, 해당 formQuestionFeedback의 북마크 필드를 교체합니다.
	 *
	 * @param formQuestionFeedbackId bookmark를 남길 formQuestionFeedback의 id
	 */
	void replaceBookmark(Long formQuestionFeedbackId);

}
