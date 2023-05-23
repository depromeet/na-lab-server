package me.nalab.survey.jpa.adaptor.createfeedback;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import me.nalab.core.data.feedback.FeedbackEntity;
import me.nalab.core.data.survey.SurveyEntity;
import me.nalab.core.data.target.TargetEntity;
import me.nalab.survey.application.port.out.persistence.createfeedback.FeedbackSavePort;
import me.nalab.survey.domain.feedback.Feedback;
import me.nalab.survey.domain.survey.Survey;
import me.nalab.survey.jpa.adaptor.RandomFeedbackFixture;
import me.nalab.survey.jpa.adaptor.RandomSurveyFixture;
import me.nalab.survey.jpa.adaptor.common.mapper.FeedbackEntityMapper;
import me.nalab.survey.jpa.adaptor.common.mapper.SurveyEntityMapper;

@DataJpaTest
@EnableJpaRepositories
@EntityScan("me.nalab.core.data")
@ContextConfiguration(classes = FeedbackSaveAdaptor.class)
@TestPropertySource("classpath:h2.properties")
class FeedbackSaveAdaptorTest {

	@Autowired
	private FeedbackSavePort feedbackSavePort;

	@Autowired
	private TestSurveyJpaRepository testSurveyJpaRepository;

	@Autowired
	private TestTargetJpaRepository testTargetJpaRepository;

	@Autowired
	private TestFeedbackJpaRepository testFeedbackJpaRepository;

	@Test
	@DisplayName("FeedbackEntity 저장 성공 테스트")
	void SAVE_FEEDBACK_ENTITY_SUCCESS() {
		// given
		TargetEntity targetEntity = getDefaultTargetEntity();
		Survey survey = RandomSurveyFixture.createRandomSurvey();
		SurveyEntity surveyEntity = SurveyEntityMapper.toSurveyEntity(targetEntity.getId(), survey);
		Feedback feedback = RandomFeedbackFixture.getRandomFeedbackBySurvey(survey);

		testTargetJpaRepository.saveAndFlush(targetEntity);
		testSurveyJpaRepository.saveAndFlush(surveyEntity);

		// when
		feedbackSavePort.saveFeedback(feedback);
		FeedbackEntity feedbackEntity = testFeedbackJpaRepository.findAll().get(0);

		// then
		assertEquals(feedback, FeedbackEntityMapper.toDomain(feedbackEntity));
	}

	private TargetEntity getDefaultTargetEntity() {
		return TargetEntity.builder()
			.id(101L)
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.nickname("test target")
			.build();
	}

}
