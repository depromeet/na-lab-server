package me.nalab.survey.web.adaptor.createsurvey;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import me.nalab.survey.application.common.survey.dto.ChoiceDto;
import me.nalab.survey.application.common.survey.dto.ChoiceFormQuestionDto;
import me.nalab.survey.application.common.survey.dto.ChoiceFormQuestionDtoType;
import me.nalab.survey.application.common.survey.dto.FormQuestionDtoable;
import me.nalab.survey.application.common.survey.dto.QuestionDtoType;
import me.nalab.survey.application.common.survey.dto.ShortFormQuestionDto;
import me.nalab.survey.application.common.survey.dto.ShortFormQuestionDtoType;
import me.nalab.survey.application.common.survey.dto.SurveyDto;
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
		LocalDateTime now = LocalDateTime.now();
		return SurveyDto.builder()
			.createdAt(now)
			.updatedAt(now)
			.formQuestionDtoableList(
				toFormQuestionDtoableList(now, surveyCreateRequest.getFormQuestionRequestableList())
			)
			.build();
	}

	private static List<FormQuestionDtoable> toFormQuestionDtoableList(LocalDateTime now,
		List<FormQuestionRequestable> formQuestionRequestableList) {
		return formQuestionRequestableList.stream()
			.map(fqr -> toFormQuestionDtoable(now, fqr))
			.collect(Collectors.toList());
	}

	private static FormQuestionDtoable toFormQuestionDtoable(LocalDateTime now,
		FormQuestionRequestable formQuestionRequestable) {
		if(formQuestionRequestable.getQuestionFormType() == QuestionDtoType.CHOICE) {
			return toChoiceFormQuestionDto(now, (ChoiceFormQuestionRequest)formQuestionRequestable);
		}
		return toShortFormQuestionDto(now, (ShortFormQuestionRequest)formQuestionRequestable);
	}

	private static ChoiceFormQuestionDto toChoiceFormQuestionDto(LocalDateTime now,
		ChoiceFormQuestionRequest choiceFormQuestionRequest) {
		return ChoiceFormQuestionDto.builder()
			.title(choiceFormQuestionRequest.getTitle())
			.questionDtoType(choiceFormQuestionRequest.getQuestionFormType())
			.order(choiceFormQuestionRequest.getOrder())
			.createdAt(now)
			.updatedAt(now)
			.maxSelectionCount(choiceFormQuestionRequest.getMaxSelectableCount())
			.choiceFormQuestionDtoType(getChoiceFormQuestionDtoType(choiceFormQuestionRequest))
			.choiceDtoList(toChoiceDtoList(choiceFormQuestionRequest.getChoiceRequestList()))
			.build();
	}

	private static ChoiceFormQuestionDtoType getChoiceFormQuestionDtoType(
		ChoiceFormQuestionRequest choiceFormQuestionRequest) {

		return Stream.of(ChoiceFormQuestionDtoType.values())
			.filter(cft -> cft.name().equalsIgnoreCase(choiceFormQuestionRequest.getChoiceFormQuestionType()))
			.findAny()
			.orElse(ChoiceFormQuestionDtoType.CUSTOM);
	}

	private static List<ChoiceDto> toChoiceDtoList(List<ChoiceRequest> choiceRequestList) {
		return choiceRequestList.stream()
			.map(SurveyCreateRequestMapper::toChoiceDto)
			.collect(Collectors.toList());
	}

	private static ChoiceDto toChoiceDto(ChoiceRequest choiceRequest) {
		return ChoiceDto.builder()
			.content(choiceRequest.getContent())
			.order(choiceRequest.getOrder())
			.build();
	}

	private static ShortFormQuestionDto toShortFormQuestionDto(LocalDateTime now,
		ShortFormQuestionRequest shortFormQuestionRequest) {
		return ShortFormQuestionDto.builder()
			.title(shortFormQuestionRequest.getTitle())
			.questionDtoType(shortFormQuestionRequest.getQuestionFormType())
			.order(shortFormQuestionRequest.getOrder())
			.createdAt(now)
			.updatedAt(now)
			.shortFormQuestionDtoType(getShortFormQuestionDtoType(shortFormQuestionRequest))
			.build();
	}

	private static ShortFormQuestionDtoType getShortFormQuestionDtoType(ShortFormQuestionRequest shortFormQuestionRequest){
		return Stream.of(ShortFormQuestionDtoType.values())
			.filter(sft -> sft.name().equalsIgnoreCase(shortFormQuestionRequest.getShortFormQuestionType()))
			.findAny()
			.orElse(ShortFormQuestionDtoType.CUSTOM);
	}

}
