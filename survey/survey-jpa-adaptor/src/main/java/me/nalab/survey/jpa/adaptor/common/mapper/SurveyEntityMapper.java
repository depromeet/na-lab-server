package me.nalab.survey.jpa.adaptor.common.mapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import me.nalab.core.data.survey.ChoiceEntity;
import me.nalab.core.data.survey.ChoiceFormQuestionEntity;
import me.nalab.core.data.survey.ChoiceFormQuestionEntityType;
import me.nalab.core.data.survey.FormQuestionEntity;
import me.nalab.core.data.survey.QuestionEntityType;
import me.nalab.core.data.survey.ShortFormQuestionEntity;
import me.nalab.core.data.survey.ShortFormQuestionEntityType;
import me.nalab.core.data.survey.SurveyEntity;
import me.nalab.survey.domain.survey.Choice;
import me.nalab.survey.domain.survey.ChoiceFormQuestion;
import me.nalab.survey.domain.survey.ChoiceFormQuestionType;
import me.nalab.survey.domain.survey.FormQuestionable;
import me.nalab.survey.domain.survey.QuestionType;
import me.nalab.survey.domain.survey.ShortFormQuestion;
import me.nalab.survey.domain.survey.ShortFormQuestionType;
import me.nalab.survey.domain.survey.Survey;

public final class SurveyEntityMapper {

	private SurveyEntityMapper(){
		throw new UnsupportedOperationException("Cannot invoke constructor \"SurveyEntityMapper()\"");
	}

	public static SurveyEntity toSurveyEntity(Long targetId, Survey survey) {
		return SurveyEntity.builder()
			.id(survey.getId())
			.targetId(targetId)
			.createdAt(survey.getCreatedAt())
			.updatedAt(survey.getUpdatedAt())
			.formQuestionableList(toFormQuestionEntityList(survey.getFormQuestionableList()))
			.build();
	}

	private static List<FormQuestionEntity> toFormQuestionEntityList(List<FormQuestionable> formQuestionableList) {
		return formQuestionableList.stream()
			.map(SurveyEntityMapper::toFormQuestionEntity)
			.collect(Collectors.toList());
	}

	private static FormQuestionEntity toFormQuestionEntity(FormQuestionable formQuestionable) {
		if(formQuestionable.getQuestionType() == QuestionType.CHOICE) {
			return toChoiceFormQuestionEntity((ChoiceFormQuestion)formQuestionable);
		}
		return toShortFormQuestionEntity((ShortFormQuestion)formQuestionable);
	}

	private static ChoiceFormQuestionEntity toChoiceFormQuestionEntity(ChoiceFormQuestion choiceFormQuestion) {
		return ChoiceFormQuestionEntity.builder()
			.id(choiceFormQuestion.getId())
			.title(choiceFormQuestion.getTitle())
			.questionType(QuestionEntityType.CHOICE)
			.createdAt(choiceFormQuestion.getCreatedAt())
			.updatedAt(choiceFormQuestion.getUpdatedAt())
			.order(choiceFormQuestion.getOrder())
			.choiceFormQuestionType(getChoiceFormQuestionEntityType(choiceFormQuestion))
			.choiceList(toChoiceEntityList(choiceFormQuestion.getChoiceList()))
			.maxSelectableCount(choiceFormQuestion.getMaxSelectableCount())
			.build();
	}

	private static ChoiceFormQuestionEntityType getChoiceFormQuestionEntityType(ChoiceFormQuestion choiceFormQuestion) {
		return Stream.of(ChoiceFormQuestionEntityType.values())
			.filter(cfqet -> cfqet.name().equalsIgnoreCase(choiceFormQuestion.getChoiceFormQuestionType().name()))
			.findAny().orElseThrow(() -> {
				throw new IllegalStateException(
					"Cannot matched choice form question domain type -> entity type \""
						+ choiceFormQuestion.getChoiceFormQuestionType() + "\"");
			});
	}

	private static List<ChoiceEntity> toChoiceEntityList(List<Choice> choiceList) {
		return choiceList.stream()
			.map(SurveyEntityMapper::toChoiceEntity)
			.collect(Collectors.toList());
	}

	private static ChoiceEntity toChoiceEntity(Choice choice) {
		return ChoiceEntity.builder()
			.id(choice.getId())
			.order(choice.getOrder())
			.content(choice.getContent())
			.build();
	}

	private static ShortFormQuestionEntity toShortFormQuestionEntity(ShortFormQuestion shortFormQuestion) {
		return ShortFormQuestionEntity.builder()
			.id(shortFormQuestion.getId())
			.title(shortFormQuestion.getTitle())
			.questionType(QuestionEntityType.SHORT)
			.createdAt(shortFormQuestion.getCreatedAt())
			.updatedAt(shortFormQuestion.getUpdatedAt())
			.order(shortFormQuestion.getOrder())
			.shortFormQuestionType(getShortFormQuestionEntityType(shortFormQuestion))
			.build();
	}

	private static ShortFormQuestionEntityType getShortFormQuestionEntityType(ShortFormQuestion shortFormQuestion) {
		return Stream.of(ShortFormQuestionEntityType.values())
			.filter(sfqet -> sfqet.name().equalsIgnoreCase(shortFormQuestion.getShortFormQuestionType().name()))
			.findAny().orElseThrow(() -> {
				throw new IllegalStateException(
					"Cannot matched short form question domain type -> entity type \""
						+ shortFormQuestion.getShortFormQuestionType() + "\"");
			});
	}

	public static Survey toSurvey(SurveyEntity surveyEntity) {
		return Survey.builder()
			.id(surveyEntity.getId())
			.createdAt(surveyEntity.getCreatedAt())
			.updatedAt(surveyEntity.getUpdatedAt())
			.formQuestionableList(toFormQuestionableList(surveyEntity.getFormQuestionableList()))
			.build();
	}

	private static List<FormQuestionable> toFormQuestionableList(List<FormQuestionEntity> formQuestionEntityList) {
		return formQuestionEntityList.stream()
			.map(SurveyEntityMapper::toFormQuestionable)
			.collect(Collectors.toList());
	}

	private static FormQuestionable toFormQuestionable(FormQuestionEntity formQuestionEntity) {
		if(formQuestionEntity.getQuestionType() == QuestionEntityType.CHOICE) {
			return toChoiceFormQuestion((ChoiceFormQuestionEntity)formQuestionEntity);
		}
		return toShortFormQuestion((ShortFormQuestionEntity)formQuestionEntity);
	}

	private static ChoiceFormQuestion toChoiceFormQuestion(ChoiceFormQuestionEntity choiceFormQuestionEntity) {
		return ChoiceFormQuestion.builder()
			.id(choiceFormQuestionEntity.getId())
			.questionType(QuestionType.CHOICE)
			.title(choiceFormQuestionEntity.getTitle())
			.order(choiceFormQuestionEntity.getOrder())
			.maxSelectableCount(choiceFormQuestionEntity.getMaxSelectableCount())
			.createdAt(choiceFormQuestionEntity.getCreatedAt())
			.updatedAt(choiceFormQuestionEntity.getUpdatedAt())
			.choiceList(toChoiceList(choiceFormQuestionEntity.getChoiceList()))
			.choiceFormQuestionType(getChoiceFormQuestionType(choiceFormQuestionEntity.getChoiceFormQuestionType()))
			.build();
	}

	private static ChoiceFormQuestionType getChoiceFormQuestionType(
		ChoiceFormQuestionEntityType choiceFormQuestionEntityType) {

		return Stream.of(ChoiceFormQuestionType.values())
			.filter(cfqt -> cfqt.name().equalsIgnoreCase(choiceFormQuestionEntityType.name()))
			.findAny()
			.orElseThrow(() -> {
				throw new IllegalStateException(
					"Cannot matched choice form question entity type -> domain type \""
						+ choiceFormQuestionEntityType + "\"");
			});
	}

	private static List<Choice> toChoiceList(List<ChoiceEntity> choiceEntityList) {
		return choiceEntityList.stream()
			.map(SurveyEntityMapper::toChoice)
			.collect(Collectors.toList());
	}

	private static Choice toChoice(ChoiceEntity choiceEntity) {
		return Choice.builder()
			.id(choiceEntity.getId())
			.order(choiceEntity.getOrder())
			.content(choiceEntity.getContent())
			.build();
	}

	private static ShortFormQuestion toShortFormQuestion(ShortFormQuestionEntity shortFormQuestionEntity) {
		return ShortFormQuestion.builder()
			.id(shortFormQuestionEntity.getId())
			.questionType(QuestionType.SHORT)
			.title(shortFormQuestionEntity.getTitle())
			.order(shortFormQuestionEntity.getOrder())
			.createdAt(shortFormQuestionEntity.getCreatedAt())
			.updatedAt(shortFormQuestionEntity.getUpdatedAt())
			.shortFormQuestionType(getShortFormQuestionType(shortFormQuestionEntity.getShortFormQuestionType()))
			.build();
	}

	private static ShortFormQuestionType getShortFormQuestionType(
		ShortFormQuestionEntityType shortFormQuestionEntityType) {

		return Stream.of(ShortFormQuestionType.values())
			.filter(sfqt -> sfqt.name().equalsIgnoreCase(shortFormQuestionEntityType.name()))
			.findAny()
			.orElseThrow(() -> {
				throw new IllegalStateException(
					"Cannot matched short form question entity type -> domain type \""
						+ shortFormQuestionEntityType + "\"");
			});
	}

}
