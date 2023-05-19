package me.nalab.survey.jpa.adaptor.persistence;

import static me.nalab.survey.jpa.adaptor.RandomSurveyFixture.createRandomSurvey;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import me.nalab.core.data.survey.SurveyEntity;
import me.nalab.core.data.target.TargetEntity;
import me.nalab.survey.domain.survey.Survey;
import me.nalab.survey.domain.target.Target;
import me.nalab.survey.jpa.adaptor.common.mapper.SurveyEntityMapper;
import me.nalab.survey.jpa.adaptor.common.mapper.TargetEntityMapper;
import me.nalab.survey.jpa.adaptor.repository.SurveyRepository;
import me.nalab.survey.jpa.adaptor.repository.TargetRepository;

class SurveyFindAdaptorTest {

	@Mock
	private SurveyRepository surveyRepository;

	@Mock
	private TargetRepository targetRepository;

	@Autowired
	private SurveyFindAdaptor surveyFindAdaptor;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		surveyFindAdaptor = new SurveyFindAdaptor(surveyRepository, targetRepository);
	}

	@Test
	void GET_SURVEY_TEST() {
		Survey expectedSurvey = createRandomSurvey();
		Long surveyId = expectedSurvey.getId();
		Long targetId = 1L;
		SurveyEntity surveyEntity = SurveyEntityMapper.toSurveyEntity(targetId, expectedSurvey);

		when(surveyRepository.findById(surveyId)).thenReturn(Optional.of(surveyEntity));

		Survey resultSurvey = surveyFindAdaptor.getSurvey(surveyId);

		assertThat(resultSurvey).isEqualTo(expectedSurvey);
	}

	@Test
	void GET_TARGET_TEST() {
		Target expectedTarget = Target.builder()
			.id(1L)
			.nickname("sujin")
			.build();
		Long targetId = expectedTarget.getId();
		TargetEntity targetEntity = TargetEntityMapper.toTargetEntity(expectedTarget);

		when(targetRepository.findById(targetId)).thenReturn(Optional.of(targetEntity));

		Target resultTarget = surveyFindAdaptor.getTarget(targetId);

		assertThat(resultTarget).isEqualTo(expectedTarget);
	}

	@Test
	void GET_TARGET_ID_TEST() {
		Survey expectedSurvey = createRandomSurvey();
		Long surveyId = expectedSurvey.getId();
		Long expectedTargetId = 1L;
		SurveyEntity surveyEntity = SurveyEntityMapper.toSurveyEntity(expectedTargetId, expectedSurvey);

		when(surveyRepository.findById(surveyId)).thenReturn(Optional.of(surveyEntity));

		Long resultTargetId = surveyFindAdaptor.getTargetId(surveyId);

		assertThat(resultTargetId).isEqualTo(expectedTargetId);
	}
}
