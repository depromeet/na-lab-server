package me.nalab.survey.web.adaptor.survey.find.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import me.nalab.survey.application.common.dto.ChoiceFormQuestionDto;
import me.nalab.survey.application.common.dto.FormQuestionDtoable;
import me.nalab.survey.application.common.dto.ShortFormQuestionDto;
import me.nalab.survey.application.common.dto.SurveyDto;
import me.nalab.survey.application.common.dto.TargetDto;
import me.nalab.survey.web.adaptor.survey.find.response.ChoiceFormQuestionResponse;
import me.nalab.survey.web.adaptor.survey.find.response.ChoiceResponse;
import me.nalab.survey.web.adaptor.survey.find.response.SurveyFindResponse;
import me.nalab.survey.web.adaptor.survey.find.response.FormQuestionResponseable;
import me.nalab.survey.web.adaptor.survey.find.response.QuestionResponseType;
import me.nalab.survey.web.adaptor.survey.find.response.ShortFormQuestionResponse;
import me.nalab.survey.web.adaptor.survey.find.response.TargetResponse;

@Service
public class SurveyFindResponseMapper {
	public SurveyFindResponse toSurveyFindResponse(TargetDto targetDto, SurveyDto surveyDto) {
		return SurveyFindResponse.builder()
			.surveyId(surveyDto.getId())
			.target(TargetResponse.builder().id(targetDto.getId()).nickname(targetDto.getNickname()).build())
			.questionCount(surveyDto.getFormQuestionDtoableList().size())
			.question(surveyDto.getFormQuestionDtoableList()
				.stream()
				.map(SurveyFindResponseMapper::toFormQuestionResponseable)
				.collect(Collectors.toList()))
			.build();
	}

	public static FormQuestionResponseable toFormQuestionResponseable(FormQuestionDtoable formQuestionDtoable) {
		if(formQuestionDtoable instanceof ChoiceFormQuestionDto) {
			return toChoiceFormQuestionResponse((ChoiceFormQuestionDto)formQuestionDtoable);
		}
		return toShortFormQuestionResponse((ShortFormQuestionDto)formQuestionDtoable);
	}

	public static ChoiceFormQuestionResponse toChoiceFormQuestionResponse(ChoiceFormQuestionDto choiceFormQuestionDto) {
		return ChoiceFormQuestionResponse.builder()
			.questionId(choiceFormQuestionDto.getId())
			.type(QuestionResponseType.toResponseType(choiceFormQuestionDto.getQuestionDtoType()))
			.title(choiceFormQuestionDto.getTitle())
			.order(choiceFormQuestionDto.getOrder())
			.maxSelectionCount(choiceFormQuestionDto.getMaxSelectionCount())
			.choices(choiceFormQuestionDto.getChoiceDtoList()
				.stream()
				.map(it -> ChoiceResponse.builder()
					.choiceId(it.getId())
					.content(it.getContent())
					.order(it.getOrder())
					.build())
				.collect(Collectors.toList()))
			.build();
	}

	public static ShortFormQuestionResponse toShortFormQuestionResponse(ShortFormQuestionDto shortFormQuestionDto) {
		return ShortFormQuestionResponse.builder()
			.questionId(shortFormQuestionDto.getId())
			.type(QuestionResponseType.toResponseType(shortFormQuestionDto.getQuestionDtoType()))
			.title(shortFormQuestionDto.getTitle())
			.order(shortFormQuestionDto.getOrder())
			.build();
	}

}
