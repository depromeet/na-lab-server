package me.nalab.survey.domain.survey;

import java.time.LocalDateTime;
import java.util.function.Function;

import me.nalab.core.idgenerator.idcore.IdGenerator;

/**
 * 주관식 질문의 타입들
 */
public enum ShortFormQuestionType {

	/**
	 * 기본타입 `나의 강점`
	 */
	STRENGTH(t -> ShortFormQuestion.builder()
		.id(t.generate())
		.questionType(QuestionType.SHORT)
		.title("나의 직무적 강점은 무엇인가요?")
		.shortFormQuestionType(SelfReferenceSupporter.STRENGTH)
		.order(4)
		.createdAt(LocalDateTime.now())
		.updatedAt(LocalDateTime.now())
		.build()),
	/**
	 * 기본타입 `나의 약점`
	 */
	WEAKNESS(t -> ShortFormQuestion.builder()
		.id(t.generate())
		.questionType(QuestionType.SHORT)
		.title("나의 직무적 약점은 무엇인가요?")
		.shortFormQuestionType(SelfReferenceSupporter.WEAKNESS)
		.order(5)
		.createdAt(LocalDateTime.now())
		.updatedAt(LocalDateTime.now())
		.build()),
	/**
	 * 커스텀 타입 `타겟이 생성한 주관식 질문`
	 */
	CUSTOM(t -> {
		throw new UnsupportedOperationException(
			"Un supported operation \"ShortFormQuestionType.CUSTOM.getShortFormQuestion()\""
		);
	}),
	;

	private final Function<IdGenerator, ShortFormQuestion> shortFormQuestionFunction;

	ShortFormQuestionType(Function<IdGenerator, ShortFormQuestion> shortFormQuestionFunction) {
		this.shortFormQuestionFunction = shortFormQuestionFunction;
	}

	ShortFormQuestion getShortFormQuestion(IdGenerator idGenerator) {
		return shortFormQuestionFunction.apply(idGenerator);
	}

	private static final class SelfReferenceSupporter {

		private static final ShortFormQuestionType STRENGTH = ShortFormQuestionType.STRENGTH;
		private static final ShortFormQuestionType WEAKNESS = ShortFormQuestionType.WEAKNESS;

	}

}
