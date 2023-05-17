package me.nalab.survey.web.adaptor.survey.find.response;

import java.util.EnumMap;
import java.util.Map;

import me.nalab.survey.application.common.dto.QuestionDtoType;

public enum QuestionResponseType {

	/**
	 * 객관식 질문
	 */
	CHOICE(QuestionDtoType.CHOICE),
	/**
	 * 주관식 질문
	 */
	SHORT(QuestionDtoType.SHORT),
	;

	private final QuestionDtoType questionDtoType;

	QuestionResponseType(QuestionDtoType questionDtoType) {
		Converter.CONVERTER_MAP.put(questionDtoType, this);
		this.questionDtoType = questionDtoType;
	}

	public static QuestionResponseType toResponseType(QuestionDtoType questionDtoType) {
		return Converter.CONVERTER_MAP.get(questionDtoType);
	}

	public QuestionDtoType toDtoType() {
		return questionDtoType;
	}

	private static final class Converter {
		private static final Map<QuestionDtoType, QuestionResponseType> CONVERTER_MAP
			= new EnumMap<>(QuestionDtoType.class);
	}

}
