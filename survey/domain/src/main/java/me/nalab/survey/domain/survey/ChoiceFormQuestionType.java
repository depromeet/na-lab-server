package me.nalab.survey.domain.survey;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

import me.nalab.core.idgenerator.idcore.IdGenerator;

/**
 * 객관식 질문의 타입들
 */
public enum ChoiceFormQuestionType {

	/**
	 * 기본타입 `나와의 협업 경험 유무`
	 */
	COLLABORATION_EXPERIENCE(t -> ChoiceFormQuestion.builder()
		.id(t.generate())
		.title("협업 경험")
		.order(1)
		.choiceFormQuestionType(SelfReferenceSupporter.COLLABORATION_EXPERIENCE)
		.questionType(QuestionType.CHOICE)
		.createdAt(LocalDateTime.now())
		.updatedAt(LocalDateTime.now())
		.maxSelectionCount(1)
		.choiceList(
			List.of(
				Choice.builder().id(t.generate()).content("네, 있어요").order(1).build()
				, Choice.builder().id(t.generate()).content("없어요").order(2).build()
			)
		).build()
	),
	/**
	 * 기본타입 `리뷰어의 직군`
	 */
	POSITION(t -> ChoiceFormQuestion.builder()
		.id(t.generate())
		.title("포지션")
		.order(2)
		.choiceFormQuestionType(SelfReferenceSupporter.POSITION)
		.questionType(QuestionType.CHOICE)
		.createdAt(LocalDateTime.now())
		.updatedAt(LocalDateTime.now())
		.maxSelectionCount(1)
		.choiceList(
			List.of(
				Choice.builder().id(t.generate()).content("기획자").order(1).build()
				, Choice.builder().id(t.generate()).content("디자이너").order(2).build()
				, Choice.builder().id(t.generate()).content("개발자").order(3).build()
				, Choice.builder().id(t.generate()).content("해당 없음").order(4).build()
			)
		).build()),
	/**
	 * 기본타입 `나의 성향`
	 */
	TENDENCY(t -> ChoiceFormQuestion.builder()
		.id(t.generate())
		.title("나의 성향")
		.order(3)
		.choiceFormQuestionType(SelfReferenceSupporter.TENDENCY)
		.questionType(QuestionType.CHOICE)
		.createdAt(LocalDateTime.now())
		.updatedAt(LocalDateTime.now())
		.maxSelectionCount(5)
		.choiceList(
			List.of(
				Choice.builder().id(t.generate()).content("꼼꼼한").order(1).build()
				, Choice.builder().id(t.generate()).content("리더십 있는").order(2).build()
				, Choice.builder().id(t.generate()).content("공감을 잘하는").order(3).build()
				, Choice.builder().id(t.generate()).content("도전적인").order(4).build()
				, Choice.builder().id(t.generate()).content("논리적인").order(5).build()
				, Choice.builder().id(t.generate()).content("섬세한").order(6).build()
				, Choice.builder().id(t.generate()).content("창의적인").order(7).build()
			)
		).build()),
	/**
	 * 커스텀 타입 `타겟이 생성한 객관식 질문`
	 */
	CUSTOM(t -> {
		throw new UnsupportedOperationException(
			"Un supported operation \"ChoiceFormQuestionType.CUSTOM.getChoiceFormQuestion()\""
		);
	}),
	;

	private final Function<IdGenerator, ChoiceFormQuestion> choiceFormQuestionFunction;

	ChoiceFormQuestionType(Function<IdGenerator, ChoiceFormQuestion> choiceFormQuestionFunction) {
		this.choiceFormQuestionFunction = choiceFormQuestionFunction;
	}

	ChoiceFormQuestion getChoiceFormQuestion(IdGenerator idGenerator) {
		return choiceFormQuestionFunction.apply(idGenerator);
	}

	private static final class SelfReferenceSupporter {

		private static final ChoiceFormQuestionType COLLABORATION_EXPERIENCE = ChoiceFormQuestionType.COLLABORATION_EXPERIENCE;
		private static final ChoiceFormQuestionType POSITION = ChoiceFormQuestionType.POSITION;
		private static final ChoiceFormQuestionType TENDENCY = ChoiceFormQuestionType.TENDENCY;

	}

}
