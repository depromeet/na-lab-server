package me.nalab.survey.jpa.adaptor.findspecific;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import me.nalab.core.data.survey.SurveyEntity;
import me.nalab.survey.application.port.out.persistence.findspecific.SurveyFindPort;
import me.nalab.survey.domain.survey.Survey;
import me.nalab.survey.jpa.adaptor.common.mapper.SurveyEntityMapper;
import me.nalab.survey.jpa.adaptor.findspecific.repository.SurveyFindJpaRepository;

@Repository("findspecific.SurveyFindAdaptor")
@RequiredArgsConstructor
public class SurveyFindAdaptor implements SurveyFindPort {

	private final SurveyFindJpaRepository surveyFindJpaRepository;

	@Override
	public Optional<Survey> findSurvey(Long surveyId) {
		Optional<SurveyEntity> surveyEntity = surveyFindJpaRepository.findById(surveyId);
		if(surveyEntity.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(SurveyEntityMapper.toSurvey(surveyEntity.get()));
	}

	@Override
	public Optional<Long> findTargetIdBySurveyId(Long surveyId) {
		Optional<SurveyEntity> surveyEntity = surveyFindJpaRepository.findById(surveyId);
		if(surveyEntity.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(surveyEntity.get().getTargetId());
	}
}
