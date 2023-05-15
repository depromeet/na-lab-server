package me.nalab.core.data.survey;

/**
 * 객관식 질문의 선택가능한 Type 들
 *
 * @author devxb
 */
public enum ShortFormQuestionType {

	/**
	 * 주관식 기본질문중 `나의 강점` Type
	 */
	STRENGTH,
	/**
	 * 주관식 기본질문중 `나의 약점` Type
	 */
	WEAKNESS,
	/**
	 * 타겟이 생성한 새로운 주관식 질문
	 */
	CUSTOM,

}
