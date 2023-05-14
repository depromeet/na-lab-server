package me.nalab.survey.domain.survey;

/**
 * 객관식 질문의 타입들
 */
public enum ChoiceFormQuestionType {

	/**
	 * 기본타입 `나와의 협업 경험 유무`
	 */
	COLLABORATION_EXPERIENCE,
	/**
	 * 기본타입 `리뷰어의 직군`
	 */
	POSITION,
	/**
	 * 기본타입 `나의 성향`
	 */
	TENDENCY,
	/**
	 * 커스텀 타입 `타겟이 생성한 객관식 질문`
	 */
	CUSTOM,

}
