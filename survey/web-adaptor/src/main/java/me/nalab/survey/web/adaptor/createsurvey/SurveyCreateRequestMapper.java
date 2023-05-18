package me.nalab.survey.web.adaptor.createsurvey;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import me.nalab.survey.application.common.dto.ChoiceDto;
import me.nalab.survey.application.common.dto.ChoiceFormQuestionDto;
import me.nalab.survey.application.common.dto.ChoiceFormQuestionDtoType;
import me.nalab.survey.application.common.dto.FormQuestionDtoable;
import me.nalab.survey.application.common.dto.QuestionDtoType;
import me.nalab.survey.application.common.dto.ShortFormQuestionDto;
import me.nalab.survey.application.common.dto.ShortFormQuestionDtoType;
import me.nalab.survey.application.common.dto.SurveyDto;
import me.nalab.survey.web.adaptor.createsurvey.request.ChoiceFormQuestionRequest;
import me.nalab.survey.web.adaptor.createsurvey.request.ChoiceRequest;
import me.nalab.survey.web.adaptor.createsurvey.request.FormQuestionRequestable;
import me.nalab.survey.web.adaptor.createsurvey.request.ShortFormQuestionRequest;
import me.nalab.survey.web.adaptor.createsurvey.request.SurveyCreateRequest;

final class SurveyCreateRequestMapper {

	private SurveyCreateRequestMapper() {
		throw new UnsupportedOperationException("Cannot invoke constructor \"SurveyCreateRequestMapper()\"");
	}

	static SurveyDto toSurveyDto(SurveyCreateRequest surveyCreateRequest) {
		LocalDateTime createdAt = LocalDateTime.now();
		return SurveyDto.builder()
			.createdAt(createdAt)
			.updatedAt(createdAt)
			.formQuestionDtoableList(
				surveyCreateRequest.getFormQuestionRequestableList()
					.stream()
					.map(fqr -> toFormQuestionDtoable(createdAt, fqr))
					.collect(Collectors.toList())
			)
			.build();
	}

	private static FormQuestionDtoable toFormQuestionDtoable(LocalDateTime createdAt,
		FormQuestionRequestable formQuestionRequestable) {
		if(formQuestionRequestable.getQuestionFormType().toUpperCase().equals(QuestionDtoType.CHOICE.name())) {
			return toChoiceFormQuestionDto(createdAt, (ChoiceFormQuestionRequest)formQuestionRequestable);
		}
		return toShortFormQuestionDto(createdAt, (ShortFormQuestionRequest)formQuestionRequestable);
	}

	private static ChoiceFormQuestionDto toChoiceFormQuestionDto(LocalDateTime createdAt,
		ChoiceFormQuestionRequest choiceFormQuestionRequest) {
		return ChoiceFormQuestionDto.builder()
			.title(choiceFormQuestionRequest.getTitle())
			.questionDtoType(QuestionDtoType.CHOICE)
			.order(choiceFormQuestionRequest.getOrder())
			.createdAt(createdAt)
			.updatedAt(createdAt)
			.maxSelectionCount(choiceFormQuestionRequest.getMaxSelectableCount())
			.choiceFormQuestionDtoType(
				Stream.of(ChoiceFormQuestionDtoType.values())
					.filter(
						cft -> cft.name().toUpperCase().equals(choiceFormQuestionRequest.getChoiceFormQuestionType())
					)
					.findAny()
					.orElse(ChoiceFormQuestionDtoType.CUSTOM)
			)
			.choiceDtoList(
				choiceFormQuestionRequest.getChoiceRequestList().stream()
					.map(SurveyCreateRequestMapper::toChoiceDto)
					.collect(Collectors.toList())
			)
			.build();
	}

	private static ChoiceDto toChoiceDto(ChoiceRequest choiceRequest) {
		return ChoiceDto.builder()
			.content(choiceRequest.getContent())
			.order(choiceRequest.getOrder())
			.build();
	}

	private static ShortFormQuestionDto toShortFormQuestionDto(LocalDateTime createdAt,
		ShortFormQuestionRequest shortFormQuestionRequest) {
		return ShortFormQuestionDto.builder()
			.title(shortFormQuestionRequest.getTitle())
			.questionDtoType(QuestionDtoType.SHORT)
			.order(shortFormQuestionRequest.getOrder())
			.createdAt(createdAt)
			.updatedAt(createdAt)
			.shortFormQuestionDtoType(
				Stream.of(ShortFormQuestionDtoType.values())
					.filter(sft -> sft.name().toUpperCase().equals(shortFormQuestionRequest.getShortFormQuestionType()))
					.findAny()
					.orElse(ShortFormQuestionDtoType.CUSTOM)
			)
			.build();
	}

}
