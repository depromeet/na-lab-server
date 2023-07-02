package me.nalab.survey.jpa.adaptor.common.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import me.nalab.core.data.survey.SurveyEntity;
import me.nalab.survey.domain.survey.Survey;
import me.nalab.survey.jpa.adaptor.RandomSurveyFixture;

class SurveyEntityMapperTest {

	@ParameterizedTest
	@MethodSource("smallSurveySources")
	void SURVEY_ENTITY_MAPPER_SMALL_SOURCES_SUCCESS(Survey survey) {
		assertSurvey(survey);
	}

	@ParameterizedTest
	@MethodSource("largeSurveySources")
	void SURVEY_ENTITY_MAPPER_LARGE_SOURCES_SUCCESS(Survey survey) {
		assertSurvey(survey);
	}

	void assertSurvey(Survey survey) {
		// given
		SurveyEntity surveyEntity = SurveyEntityMapper.toSurveyEntity(1L, survey);

		// when
		Survey doubleMapping = SurveyEntityMapper.toSurvey(surveyEntity);

		// then
		assertEquals(survey, doubleMapping);
	}

	private static Stream<Survey> smallSurveySources() {
		RandomSurveyFixture.initGenerator();
		RandomSurveyFixture.setRandomChoiceCountGenerator(() -> 2);
		RandomSurveyFixture.setRandomQuestionCountGenerator(() -> 1);
		List<Survey> surveyList = new ArrayList<>();
		for(int i = 0; i < 100; i++) {
			surveyList.add(RandomSurveyFixture.createRandomSurvey());
		}
		return surveyList.stream();
	}

	private static Stream<Survey> largeSurveySources() {
		RandomSurveyFixture.initGenerator();
		List<Survey> surveyList = new ArrayList<>();
		for(int i = 0; i < 1000; i++) {
			surveyList.add(RandomSurveyFixture.createRandomSurvey());
		}
		return surveyList.stream();
	}

}
