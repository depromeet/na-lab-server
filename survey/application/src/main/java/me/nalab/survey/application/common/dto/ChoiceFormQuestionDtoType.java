package me.nalab.survey.application.common.dto;

import java.util.EnumMap;
import java.util.Map;

import me.nalab.survey.domain.survey.ChoiceFormQuestionType;

/**
 * 객관식 질문의 타입들
 */
public enum ChoiceFormQuestionDtoType {

	/**
	 * 기본타입 `나와의 협업 경험 유무`
	 */
	COLLABORATION_EXPERIENCE(ChoiceFormQuestionType.COLLABORATION_EXPERIENCE),
	/**
	 * 기본타입 `리뷰어의 직군`
	 */
	POSITION(ChoiceFormQuestionType.POSITION),
	/**
	 * 기본타입 `나의 성향`
	 */
	TENDENCY(ChoiceFormQuestionType.TENDENCY),
	/**
	 * 커스텀 타입 `타겟이 생성한 객관식 질문`
	 */
	CUSTOM(ChoiceFormQuestionType.CUSTOM),
	;

	private final ChoiceFormQuestionType choiceFormQuestionType;

	ChoiceFormQuestionDtoType(ChoiceFormQuestionType choiceFormQuestionType) {
		this.choiceFormQuestionType = choiceFormQuestionType;
		Converter.CONVERTER_MAP.put(choiceFormQuestionType, this);
	}

	public static ChoiceFormQuestionDtoType toDtoType(ChoiceFormQuestionType choiceFormQuestionType) {
		return Converter.CONVERTER_MAP.get(choiceFormQuestionType);
	}

	public ChoiceFormQuestionType toDomainType() {
		return choiceFormQuestionType;
	}

	private static final class Converter {
		private static final Map<ChoiceFormQuestionType, ChoiceFormQuestionDtoType> CONVERTER_MAP
			= new EnumMap<>(ChoiceFormQuestionType.class);
	}

}
