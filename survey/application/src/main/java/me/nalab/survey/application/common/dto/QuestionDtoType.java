package me.nalab.survey.application.common.dto;

import java.util.EnumMap;
import java.util.Map;

import me.nalab.survey.domain.survey.QuestionType;

/**
 * 질문의 유형들
 */
public enum QuestionDtoType {

	/**
	 * 객관식 질문
	 */
	CHOICE(QuestionType.CHOICE),
	/**
	 * 주관식 질문
	 */
	SHORT(QuestionType.SHORT),
	;

	private final QuestionType questionType;

	QuestionDtoType(QuestionType questionType) {
		Converter.CONVERTER_MAP.put(questionType, this);
		this.questionType = questionType;
	}

	public static QuestionDtoType toDtoType(QuestionType questionType) {
		return Converter.CONVERTER_MAP.get(questionType);
	}

	public QuestionType toDomainType(){
		return questionType;
	}

	private static final class Converter {
		private static final Map<QuestionType, QuestionDtoType> CONVERTER_MAP
			= new EnumMap<>(QuestionType.class);
	}

}
