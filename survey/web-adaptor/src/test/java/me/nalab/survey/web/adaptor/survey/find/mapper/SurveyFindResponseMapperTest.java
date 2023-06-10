package me.nalab.survey.web.adaptor.survey.find.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import me.nalab.survey.application.common.survey.dto.ChoiceDto;
import me.nalab.survey.application.common.survey.dto.ChoiceFormQuestionDto;
import me.nalab.survey.application.common.survey.dto.ChoiceFormQuestionDtoType;
import me.nalab.survey.application.common.survey.dto.QuestionDtoType;
import me.nalab.survey.application.common.survey.dto.ShortFormQuestionDto;
import me.nalab.survey.application.common.survey.dto.ShortFormQuestionDtoType;
import me.nalab.survey.application.common.survey.dto.SurveyDto;
import me.nalab.survey.application.common.survey.dto.TargetDto;
import me.nalab.survey.web.adaptor.survey.find.response.ChoiceFormQuestionResponse;
import me.nalab.survey.web.adaptor.survey.find.response.ChoiceResponse;
import me.nalab.survey.web.adaptor.survey.find.response.FormQuestionResponseable;
import me.nalab.survey.web.adaptor.survey.find.response.ShortFormQuestionResponse;
import me.nalab.survey.web.adaptor.survey.find.response.SurveyFindResponse;
import me.nalab.survey.web.adaptor.survey.find.response.TargetResponse;

class SurveyFindResponseMapperTest {

	@Test
	void toSurveyFindResponse() {
		TargetDto targetDto = TargetDto.builder().id(1L).nickname("sujin").build();

		SurveyDto surveyDto = SurveyDto.builder()
			.id(1L)
			.formQuestionDtoableList(Arrays.asList(ChoiceFormQuestionDto.builder()
					.id(1L)
					.questionDtoType(QuestionDtoType.CHOICE)
					.choiceFormQuestionDtoType(ChoiceFormQuestionDtoType.TENDENCY)
					.title("Choice Question")
					.order(1)
					.maxSelectionCount(2)
					.choiceDtoList(Arrays.asList(ChoiceDto.builder().id(1L).content("Choice 1").order(1).build(),
						ChoiceDto.builder().id(2L).content("Choice 2").order(2).build()))
					.build(),

				ShortFormQuestionDto.builder()
					.id(2L)
					.questionDtoType(QuestionDtoType.SHORT)
					.shortFormQuestionDtoType(ShortFormQuestionDtoType.CUSTOM)
					.title("Short Question")
					.order(2)
					.build()))
			.build();

		SurveyFindResponse response = SurveyFindResponseMapper.toSurveyFindResponse(targetDto, surveyDto);
		assertEquals(surveyDto.getId(), response.getSurveyId());

		TargetResponse targetResponse = response.getTarget();
		assertAll(
			() -> assertEquals(targetDto.getId(), targetResponse.getId()),
			() -> assertEquals(targetDto.getNickname(), targetResponse.getNickname())
		);

		List<FormQuestionResponseable> questionList = response.getQuestion();
		assertEquals(surveyDto.getFormQuestionDtoableList().size(), questionList.size());

		ChoiceFormQuestionDto choiceQuestionDto = (ChoiceFormQuestionDto)surveyDto.getFormQuestionDtoableList().get(0);
		ChoiceFormQuestionResponse choiceQuestionResponse = (ChoiceFormQuestionResponse)questionList.get(0);
		assertAll(
			() -> assertEquals(choiceQuestionDto.getId(), choiceQuestionResponse.getQuestionId()),
			() -> assertEquals(QuestionDtoType.CHOICE.toString().toLowerCase(), choiceQuestionResponse.getType()),
			() -> assertEquals(choiceQuestionDto.getTitle(), choiceQuestionResponse.getTitle()),
			() -> assertEquals(choiceQuestionDto.getOrder(), choiceQuestionResponse.getOrder()),
			() -> assertEquals(choiceQuestionDto.getMaxSelectionCount(),
				choiceQuestionResponse.getMaxSelectionCount())
		);

		List<ChoiceResponse> choiceList = choiceQuestionResponse.getChoices();
		assertEquals(choiceQuestionDto.getChoiceDtoList().size(), choiceList.size());

		List<ChoiceDto> choiceDtoList = choiceQuestionDto.getChoiceDtoList();
		assertEquals(choiceDtoList.size(), choiceList.size());

		IntStream.range(0, choiceList.size()).forEach(i -> {
			ChoiceDto choiceDto = choiceDtoList.get(i);
			ChoiceResponse choiceResponse = choiceList.get(i);

			assertAll(
				() -> assertEquals(choiceDto.getId(), choiceResponse.getChoiceId()),
				() -> assertEquals(choiceDto.getContent(), choiceResponse.getContent()),
				() -> assertEquals(choiceDto.getOrder(), choiceResponse.getOrder())
			);
		});

		ShortFormQuestionDto shortQuestionDto = (ShortFormQuestionDto)surveyDto.getFormQuestionDtoableList().get(1);
		ShortFormQuestionResponse shortQuestionResponse = (ShortFormQuestionResponse)questionList.get(1);
		assertAll(
			() -> assertEquals(shortQuestionDto.getId(), shortQuestionResponse.getQuestionId()),
			() -> assertEquals(QuestionDtoType.SHORT.toString().toLowerCase(), shortQuestionResponse.getType()),
			() -> assertEquals(shortQuestionDto.getTitle(), shortQuestionResponse.getTitle()),
			() -> assertEquals(shortQuestionDto.getOrder(), shortQuestionResponse.getOrder())
		);
	}

}
