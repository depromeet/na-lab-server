package me.nalab.survey.application.port.out.persistence.create.request;

import java.util.EnumMap;
import java.util.Map;

import me.nalab.survey.domain.survey.ShortFormQuestionType;

/**
 * 주관식 질문의 타입들
 */
public enum PersistenceShortFormQuestionType {

	/**
	 * 기본타입 `나의 강점` <br>
	 * ShortFormQuestionType.STRENGTH -> PersistenceShortFormQuestionType.STRENGTH
	 */
	STRENGTH(ShortFormQuestionType.STRENGTH),
	/**
	 * 기본타입 `나의 약점` <br>
	 * ShortFormQuestionType.WEAKNESS -> PersistenceShortFormQuestionType.WEAKNESS
	 */
	WEAKNESS(ShortFormQuestionType.WEAKNESS),
	/**
	 * 커스텀 타입 `타겟이 생성한 주관식 질문` <br>
	 * ShortFormQuestionType.CUSTOM -> PersistenceShortFormQuestionType.CUSTOM
	 */
	CUSTOM(ShortFormQuestionType.CUSTOM),
	;

	PersistenceShortFormQuestionType(ShortFormQuestionType shortFormQuestionType) {
		Converter.TYPE_CONVERTER.put(shortFormQuestionType, this);
	}

	public static PersistenceShortFormQuestionType convert(ShortFormQuestionType shortFormQuestionType) {
		return Converter.TYPE_CONVERTER.get(shortFormQuestionType);
	}

	private static final class Converter {
		private static final Map<ShortFormQuestionType, PersistenceShortFormQuestionType> TYPE_CONVERTER
			= new EnumMap<>(ShortFormQuestionType.class);
	}

}
