package me.nalab.survey.application.common.mapper;

import java.util.stream.Collectors;

import me.nalab.survey.application.common.dto.ChoiceDto;
import me.nalab.survey.application.common.dto.ChoiceFormQuestionDto;
import me.nalab.survey.application.common.dto.ChoiceFormQuestionDtoType;
import me.nalab.survey.application.common.dto.FormQuestionDtoable;
import me.nalab.survey.application.common.dto.QuestionDtoType;
import me.nalab.survey.application.common.dto.ShortFormQuestionDto;
import me.nalab.survey.application.common.dto.ShortFormQuestionDtoType;
import me.nalab.survey.application.common.dto.SurveyDto;
import me.nalab.survey.domain.survey.Choice;
import me.nalab.survey.domain.survey.ChoiceFormQuestion;
import me.nalab.survey.domain.survey.FormQuestionable;
import me.nalab.survey.domain.survey.ShortFormQuestion;
import me.nalab.survey.domain.survey.Survey;

public final class SurveyDtoMapper {

	private SurveyDtoMapper(){
		throw new UnsupportedOperationException("Cannot invoke constructor \"SurveyDtoMapper()\"");
	}

	public static Survey toSurvey(SurveyDto surveyDto) {
		return Survey.builder()
			.id(surveyDto.getId())
			.createdAt(surveyDto.getCreatedAt())
			.updatedAt(surveyDto.getUpdatedAt())
			.formQuestionableList(
				surveyDto.getFormQuestionDtoableList().stream()
					.map(SurveyDtoMapper::toFormQuestionable)
					.collect(Collectors.toList())
			)
			.build();
	}

	public static FormQuestionable toFormQuestionable(FormQuestionDtoable formQuestionDtoable) {
		if(formQuestionDtoable instanceof ChoiceFormQuestionDto) {
			return toChoiceFormQuestion((ChoiceFormQuestionDto)formQuestionDtoable);
		}
		return toShortFormQuestion((ShortFormQuestionDto)formQuestionDtoable);
	}

	public static ChoiceFormQuestion toChoiceFormQuestion(ChoiceFormQuestionDto choiceFormQuestionDto) {
		return ChoiceFormQuestion.builder()
			.id(choiceFormQuestionDto.getId())
			.questionType(choiceFormQuestionDto.getQuestionDtoType().toDomainType())
			.title(choiceFormQuestionDto.getTitle())
			.order(choiceFormQuestionDto.getOrder())
			.maxSelectionCount(choiceFormQuestionDto.getMaxSelectionCount())
			.createdAt(choiceFormQuestionDto.getCreatedAt())
			.updatedAt(choiceFormQuestionDto.getUpdatedAt())
			.choiceList(choiceFormQuestionDto.getChoiceDtoList().stream()
				.map(c -> Choice.builder()
					.id(c.getId())
					.order(c.getOrder())
					.content(c.getContent())
					.build())
				.collect(Collectors.toList())
			)
			.choiceFormQuestionType(choiceFormQuestionDto.getChoiceFormQuestionDtoType().toDomainType())
			.build();
	}

	public static ShortFormQuestion toShortFormQuestion(ShortFormQuestionDto shortFormQuestionDto) {
		return ShortFormQuestion.builder()
			.id(shortFormQuestionDto.getId())
			.questionType(shortFormQuestionDto.getQuestionDtoType().toDomainType())
			.title(shortFormQuestionDto.getTitle())
			.order(shortFormQuestionDto.getOrder())
			.createdAt(shortFormQuestionDto.getCreatedAt())
			.updatedAt(shortFormQuestionDto.getUpdatedAt())
			.shortFormQuestionType(shortFormQuestionDto.getShortFormQuestionDtoType().toDomainType())
			.build();
	}

	public static SurveyDto toSurveyDto(Long targetId, Survey survey) {
		return SurveyDto.builder()
			.id(survey.getId())
			.targetId(targetId)
			.createdAt(survey.getCreatedAt())
			.updatedAt(survey.getUpdatedAt())
			.formQuestionDtoableList(
				survey.getFormQuestionableList()
					.stream()
					.map(SurveyDtoMapper::getFormQuestionDtoable)
					.collect(Collectors.toList())
			)
			.build();
	}

	public static FormQuestionDtoable getFormQuestionDtoable(FormQuestionable formQuestionable) {
		if(formQuestionable instanceof ChoiceFormQuestion) {
			return getChoiceFormQuestionDto((ChoiceFormQuestion)formQuestionable);
		}
		return getShortFormQuestionDto((ShortFormQuestion)formQuestionable);
	}

	public static ChoiceFormQuestionDto getChoiceFormQuestionDto(ChoiceFormQuestion choiceFormQuestion) {
		return ChoiceFormQuestionDto.builder()
			.id(choiceFormQuestion.getId())
			.questionDtoType(QuestionDtoType.toDtoType(choiceFormQuestion.getQuestionType()))
			.title(choiceFormQuestion.getTitle())
			.order(choiceFormQuestion.getOrder())
			.maxSelectionCount(choiceFormQuestion.getMaxSelectionCount())
			.createdAt(choiceFormQuestion.getCreatedAt())
			.updatedAt(choiceFormQuestion.getUpdatedAt())
			.choiceDtoList(choiceFormQuestion.getChoiceList().stream()
				.map(c -> ChoiceDto.builder()
					.id(c.getId())
					.order(c.getOrder())
					.content(c.getContent())
					.build())
				.collect(Collectors.toList())
			)
			.choiceFormQuestionDtoType(
				ChoiceFormQuestionDtoType.toDtoType(choiceFormQuestion.getChoiceFormQuestionType())
			)
			.build();
	}

	public static ShortFormQuestionDto getShortFormQuestionDto(ShortFormQuestion shortFormQuestion) {
		return ShortFormQuestionDto.builder()
			.id(shortFormQuestion.getId())
			.questionDtoType(QuestionDtoType.toDtoType(shortFormQuestion.getQuestionType()))
			.title(shortFormQuestion.getTitle())
			.order(shortFormQuestion.getOrder())
			.createdAt(shortFormQuestion.getCreatedAt())
			.updatedAt(shortFormQuestion.getUpdatedAt())
			.shortFormQuestionDtoType(ShortFormQuestionDtoType.toDtoType(shortFormQuestion.getShortFormQuestionType()))
			.build();
	}

}
