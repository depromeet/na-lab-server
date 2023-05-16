package me.nalab.survey.application.port.out.persistence.create.request;

import java.util.HashMap;
import java.util.Map;

import me.nalab.survey.domain.survey.QuestionType;

/**
 * 질문의 유형들
 */
public enum PersistenceQuestionType {

	/**
	 * 객관식 질문 <br>
	 * QuestionType.CHOICE -> PersistenceQuestionType.CHOICE
	 */
	CHOICE(QuestionType.CHOICE),
	/**
	 * 주관식 질문 <br>
	 * QuestionType.SHORT -> PersistenceQuestionType.SHORT
	 */
	SHORT(QuestionType.SHORT),
	;

	PersistenceQuestionType(QuestionType questionType){
		Converter.TYPE_CONVERTER.put(questionType, this);
	}

	public static PersistenceQuestionType convert(QuestionType questionType){
		return Converter.TYPE_CONVERTER.get(questionType);
	}

	private static final class Converter {
		private static final Map<QuestionType, PersistenceQuestionType> TYPE_CONVERTER = new HashMap<>();
	}

}
