package me.nalab.survey.application.port.out.persistence.create.request;

import java.util.EnumMap;
import java.util.Map;

import me.nalab.survey.domain.survey.ChoiceFormQuestionType;

/**
 * 객관식 질문의 타입들
 */
public enum PersistenceChoiceFormQuestionType {

	/**
	 * 기본타입 `나와의 협업 경험 유무` <br>
	 * ChoiceFormQuestionType.COLLABORATION_EXPERIENCE -> PersistenceChoiceFormQuestionType.COLLABORATION_EXPERIENCE
	 */
	COLLABORATION_EXPERIENCE(ChoiceFormQuestionType.COLLABORATION_EXPERIENCE),
	/**
	 * 기본타입 `리뷰어의 직군` <br>
	 * ChoiceFormQuestionType.POSITION -> PersistenceChoiceFormQuestionType.POSITION
	 */
	POSITION(ChoiceFormQuestionType.POSITION),
	/**
	 * 기본타입 `나의 성향` <br>
	 * ChoiceFormQuestionType.TENDENCY -> PersistenceChoiceFormQuestionType.TENDENCY
	 */
	TENDENCY(ChoiceFormQuestionType.TENDENCY),
	/**
	 * 커스텀 타입 `타겟이 생성한 객관식 질문` <br>
	 * ChoiceFormQuestionType.CUSTOM -> PersistenceChoiceFormQuestionType.CUSTOM
	 */
	CUSTOM(ChoiceFormQuestionType.CUSTOM),
	;

	PersistenceChoiceFormQuestionType(ChoiceFormQuestionType choiceFormQuestionType) {
		Converter.TYPE_CONVERTER.put(choiceFormQuestionType, this);
	}

	public static PersistenceChoiceFormQuestionType convert(ChoiceFormQuestionType choiceFormQuestionType) {
		return Converter.TYPE_CONVERTER.get(choiceFormQuestionType);
	}

	private static final class Converter {
		private static final Map<ChoiceFormQuestionType, PersistenceChoiceFormQuestionType> TYPE_CONVERTER
			= new EnumMap<>(ChoiceFormQuestionType.class);
	}

}
