package me.nalab.survey.application.common.dto;

import java.util.EnumMap;
import java.util.Map;

import me.nalab.survey.domain.survey.ShortFormQuestionType;

/**
 * 주관식 질문의 타입들
 */
public enum ShortFormQuestionDtoType {

	/**
	 * 기본타입 `나의 강점`
	 */
	STRENGTH(ShortFormQuestionType.STRENGTH),
	/**
	 * 기본타입 `나의 약점`
	 */
	WEAKNESS(ShortFormQuestionType.WEAKNESS),
	/**
	 * 커스텀 타입 `타겟이 생성한 주관식 질문`
	 */
	CUSTOM(ShortFormQuestionType.CUSTOM),
	;

	private final ShortFormQuestionType shortFormQuestionType;

	ShortFormQuestionDtoType(ShortFormQuestionType shortFormQuestionType) {
		Converter.CONVERTER_MAP.put(shortFormQuestionType, this);
		this.shortFormQuestionType = shortFormQuestionType;
	}

	public static ShortFormQuestionDtoType toDtoType(ShortFormQuestionType shortFormQuestionType) {
		return Converter.CONVERTER_MAP.get(shortFormQuestionType);
	}

	public ShortFormQuestionType toDomainType() {
		return shortFormQuestionType;
	}

	private static final class Converter {
		private static final Map<ShortFormQuestionType, ShortFormQuestionDtoType> CONVERTER_MAP
			= new EnumMap<>(ShortFormQuestionType.class);
	}

}
