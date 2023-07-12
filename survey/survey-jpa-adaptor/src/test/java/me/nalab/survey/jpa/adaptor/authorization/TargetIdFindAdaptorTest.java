package me.nalab.survey.jpa.adaptor.authorization;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
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
import me.nalab.survey.application.port.out.persistence.authorization.TargetIdFindPort;
import me.nalab.survey.domain.survey.Survey;
import me.nalab.survey.jpa.adaptor.RandomFeedbackFixture;
import me.nalab.survey.jpa.adaptor.RandomSurveyFixture;
import me.nalab.survey.jpa.adaptor.common.mapper.FeedbackEntityMapper;
import me.nalab.survey.jpa.adaptor.common.mapper.SurveyEntityMapper;

@DataJpaTest
@EnableJpaRepositories
@EntityScan("me.nalab.core.data")
@ContextConfiguration(classes = TargetIdFindAdaptor.class)
@TestPropertySource("classpath:h2.properties")
class TargetIdFindAdaptorTest {

	@Autowired
	private TargetIdFindPort targetIdFindPort;

	@Autowired
	private TestSurveyJpaRepository testSurveyJpaRepository;

	@Autowired
	private TestTargetJpaRepository testTargetJpaRepository;

	@Autowired
	private TestFeedbackJpaRepository testFeedbackJpaRepository;

	@BeforeEach
	void OPTIMIZE_RANDOM_FIXTURE() {
		RandomSurveyFixture.initGenerator();
		RandomSurveyFixture.setRandomQuestionCountGenerator(() -> 1);
		RandomSurveyFixture.setRandomChoiceCountGenerator(() -> 1);
	}

	@Test
	@DisplayName("저장된 surveyId로 targetId를 조회하려 할 경우, 성공한다.")
	void FIND_TARGET_ID_BY_SAVED_SURVEY_ID() {
		// given
		Long targetId = 101L;
		TargetEntity targetEntity = TargetEntity.builder()
			.id(targetId)
			.createdAt(Instant.now())
			.updatedAt(Instant.now())
			.nickname("test target")
			.build();
		Survey survey = RandomSurveyFixture.createRandomSurvey();
		SurveyEntity surveyEntity = SurveyEntityMapper.toSurveyEntity(targetId, survey);

		testTargetJpaRepository.saveAndFlush(targetEntity);
		testSurveyJpaRepository.saveAndFlush(surveyEntity);

		// when
		Optional<Long> result = targetIdFindPort.findTargetIdBySurveyId(survey.getId());

		// then
		assertThat(result).isPresent();
		assertThat(result).contains(targetId);
	}

	@Test
	@DisplayName("targetId에 해당하는 surveyId가 없다면, Optional.empty()를 반환한다.")
	void FIND_TARGET_ID_WITH_OUT_SAVED_SURVEY() {
		// given
		Long targetId = 101L;
		TargetEntity targetEntity = TargetEntity.builder()
			.id(targetId)
			.createdAt(Instant.now())
			.updatedAt(Instant.now())
			.nickname("test target")
			.build();

		testTargetJpaRepository.saveAndFlush(targetEntity);

		// when
		Optional<Long> result = targetIdFindPort.findTargetIdBySurveyId(targetId);

		// then
		assertThat(result).isNotPresent();
	}

	@Test
	@DisplayName("targetId에 해당하는 feedbackId가 있다면, 성공한다.")
	void FIND_TARGET_ID_BY_SAVED_FEEDBACK_ID() {
		// given
		Long targetId = 101L;
		TargetEntity targetEntity = TargetEntity.builder()
			.id(targetId)
			.createdAt(Instant.now())
			.updatedAt(Instant.now())
			.nickname("test target")
			.build();
		Survey survey = RandomSurveyFixture.createRandomSurvey();
		SurveyEntity surveyEntity = SurveyEntityMapper.toSurveyEntity(targetId, survey);
		List<FeedbackEntity> feedbackEntityList = List.of(
			FeedbackEntityMapper.toEntity(RandomFeedbackFixture.getRandomFeedbackBySurvey(survey)),
			FeedbackEntityMapper.toEntity(RandomFeedbackFixture.getRandomFeedbackBySurvey(survey)),
			FeedbackEntityMapper.toEntity(RandomFeedbackFixture.getRandomFeedbackBySurvey(survey))
		);

		testTargetJpaRepository.saveAndFlush(targetEntity);
		testSurveyJpaRepository.saveAndFlush(surveyEntity);
		testFeedbackJpaRepository.saveAllAndFlush(feedbackEntityList);

		// when
		List<Optional<Long>> resultList = List.of(
			targetIdFindPort.findTargetIdByFeedbackId(feedbackEntityList.get(0).getId()),
			targetIdFindPort.findTargetIdByFeedbackId(feedbackEntityList.get(1).getId()),
			targetIdFindPort.findTargetIdByFeedbackId(feedbackEntityList.get(2).getId())
		);

		// then
		resultList.forEach(r -> assertThat(r).isPresent());
		resultList.forEach(r -> assertThat(r).contains(targetId));
	}

	@Test
	@DisplayName("target이 여러명 있을때, 조회된 원하는 타겟만 반환한다.")
	void FIND_ONT_TARGET_WHEN_HAVE_MULTIPLE_TARGET() {
		// given
		Long targetId = 101L;
		TargetEntity targetEntity1 = TargetEntity.builder()
			.id(targetId)
			.createdAt(Instant.now())
			.updatedAt(Instant.now())
			.nickname("test target1")
			.build();
		Survey survey = RandomSurveyFixture.createRandomSurvey();

		SurveyEntity surveyEntity = SurveyEntityMapper.toSurveyEntity(targetId, survey);

		TargetEntity targetEntity2 = TargetEntity.builder()
			.id(102L)
			.createdAt(Instant.now())
			.updatedAt(Instant.now())
			.nickname("test target2")
			.build();

		testTargetJpaRepository.saveAndFlush(targetEntity1);
		testTargetJpaRepository.saveAndFlush(targetEntity2);
		testSurveyJpaRepository.saveAndFlush(surveyEntity);

		// when
		Optional<Long> result = targetIdFindPort.findTargetIdBySurveyId(survey.getId());

		// then
		assertThat(result).isPresent();
		assertThat(result).contains(targetId);
	}

	@Test
	@DisplayName("formQuestionFeedbackId에 해당하는 target이 있다면, 조회된 타겟의 id를 반환한다.")
	void FIND_TARGET_ID_BY_FORM_QUESTION_FEEDBACK_ID() {
		// given
		Long targetId = 101L;
		TargetEntity targetEntity = TargetEntity.builder()
			.id(targetId)
			.createdAt(Instant.now())
			.updatedAt(Instant.now())
			.nickname("test target")
			.build();
		Survey survey = RandomSurveyFixture.createRandomSurvey();
		SurveyEntity surveyEntity = SurveyEntityMapper.toSurveyEntity(targetId, survey);
		FeedbackEntity feedbackEntity = FeedbackEntityMapper.toEntity(
			RandomFeedbackFixture.getRandomFeedbackBySurvey(survey));

		testTargetJpaRepository.saveAndFlush(targetEntity);
		testSurveyJpaRepository.saveAndFlush(surveyEntity);
		testFeedbackJpaRepository.saveAndFlush(feedbackEntity);

		// when
		List<Optional<Long>> resultList = feedbackEntity.getFormFeedbackEntityList().stream()
			.map(formQuestionFeedbackEntity -> targetIdFindPort.findTargetIdByFormQuestionFeedbackId(
				formQuestionFeedbackEntity.getId()))
			.collect(Collectors.toList());

		// then
		resultList.forEach(r -> assertThat(r).isPresent());
		resultList.forEach(r -> assertThat(r).contains(targetId));
	}

}
