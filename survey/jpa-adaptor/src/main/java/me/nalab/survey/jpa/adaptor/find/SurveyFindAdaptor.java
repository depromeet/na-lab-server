package me.nalab.survey.jpa.adaptor.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import me.nalab.core.data.survey.SurveyEntity;
import me.nalab.core.data.target.TargetEntity;
import me.nalab.survey.application.port.out.persistence.survey.find.SurveyFindPort;
import me.nalab.survey.application.port.out.persistence.target.find.TargetFindPort;
import me.nalab.survey.domain.survey.Survey;
import me.nalab.survey.domain.target.Target;
import me.nalab.survey.jpa.adaptor.common.mapper.SurveyEntityMapper;
import me.nalab.survey.jpa.adaptor.common.mapper.TargetEntityMapper;
import me.nalab.survey.jpa.adaptor.exception.survey.SurveyDoesNotExistException;
import me.nalab.survey.jpa.adaptor.exception.target.TargetDoesNotExistException;
import me.nalab.survey.jpa.adaptor.repository.SurveyRepository;
import me.nalab.survey.jpa.adaptor.repository.TargetRepository;

@Repository
@RequiredArgsConstructor
public class SurveyFindAdaptor implements SurveyFindPort, TargetFindPort {

	private final SurveyRepository surveyRepository;
	private final TargetRepository targetRepository;

	@Override
	public Optional<Survey> findSurvey(Long surveyId) {
		Optional<SurveyEntity> surveyEntity = surveyRepository.findById(surveyId);
		if (surveyEntity.isEmpty()) {
			return Optional.empty();
		}
		Survey survey = SurveyEntityMapper.toSurvey(surveyEntity.get());
		return Optional.of(survey);
	}

	@Override
	public Optional<Target> findTarget(Long targetId) {
		Optional<TargetEntity> targetEntity = targetRepository.findById(targetId);
		if (targetEntity.isEmpty()) {
			return Optional.empty();
		}
		Target target = TargetEntityMapper.toTarget(targetEntity.get());
		return Optional.of(target);
	}

	@Override
	public Optional<Long> findTargetIdBySurveyId(Long surveyId) {
		Optional<SurveyEntity> surveyEntity = surveyRepository.findById(surveyId);
		if (surveyEntity.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(surveyEntity.get().getTargetId());
	}
}
