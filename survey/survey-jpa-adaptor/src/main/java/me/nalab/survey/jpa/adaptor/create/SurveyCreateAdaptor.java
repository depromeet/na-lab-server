package me.nalab.survey.jpa.adaptor.create;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import me.nalab.core.data.survey.SurveyEntity;
import me.nalab.survey.application.port.out.persistence.SurveyCreatePort;
import me.nalab.survey.domain.survey.Survey;
import me.nalab.survey.jpa.adaptor.common.mapper.SurveyEntityMapper;
import me.nalab.survey.jpa.adaptor.create.repository.SurveyCreateJpaRepository;

@Repository
@RequiredArgsConstructor
public class SurveyCreateAdaptor implements SurveyCreatePort {

	private final SurveyCreateJpaRepository surveyCreateJpaRepository;

	@Override
	public void createSurvey(Long targetId, Survey survey) {
		SurveyEntity surveyEntity = SurveyEntityMapper.toSurveyEntity(targetId, survey);
		surveyCreateJpaRepository.save(surveyEntity);
	}

}
