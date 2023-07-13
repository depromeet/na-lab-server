package me.nalab.survey.application.common.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import me.nalab.survey.application.common.survey.dto.SurveyDto;
import me.nalab.survey.application.common.survey.mapper.SurveyDtoMapper;
import me.nalab.survey.domain.survey.Survey;

class SurveyDtoMapperTest {

	@ParameterizedTest
	@MethodSource("surveyMapperSmallSources")
	void SURVEY_MAPPER_SMALL_SUCCESS(Survey survey){
		// when
		SurveyDto expectedDto = SurveyDtoMapper.toSurveyDto(null, survey);
		Survey expectedSurvey = SurveyDtoMapper.toSurvey(expectedDto);

		// then
		assertEquals(expectedDto, SurveyDtoMapper.toSurveyDto(null, expectedSurvey));
		assertEquals(expectedSurvey, SurveyDtoMapper.toSurvey(expectedDto));
	}

	private static Stream<Arguments> surveyMapperSmallSources(){
		RandomSurveyFixture.initGenerator();
		RandomSurveyFixture.setRandomChoiceCountGenerator(() -> 1);
		RandomSurveyFixture.setRandomQuestionCountGenerator(() -> 1);
		return getMapperSources(100);
	}

	@ParameterizedTest
	@MethodSource("surveyMapperSources")
	void SURVEY_DTO_MAPPER_SUCCESS(Survey survey) {
		// when
		SurveyDto expectedDto = SurveyDtoMapper.toSurveyDto(null, survey);
		Survey expectedSurvey = SurveyDtoMapper.toSurvey(expectedDto);

		// then
		assertEquals(expectedDto, SurveyDtoMapper.toSurveyDto(null, expectedSurvey));
		assertEquals(expectedSurvey, SurveyDtoMapper.toSurvey(expectedDto));
	}

	private static Stream<Arguments> surveyMapperSources() {
		RandomSurveyFixture.initGenerator();
		return getMapperSources(1000);
	}

	private static Stream<Arguments> getMapperSources(int sourceCount){
		List<Arguments> surveyList = new ArrayList<>();
		for(int i = 0; i < sourceCount; i++){
			surveyList.add(Arguments.of(RandomSurveyFixture.createRandomSurvey()));
		}
		return surveyList.stream();
	}

}
