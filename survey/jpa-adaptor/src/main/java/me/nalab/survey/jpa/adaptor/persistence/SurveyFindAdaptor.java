package me.nalab.survey.jpa.adaptor.persistence;

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
	public Survey getSurvey(Long surveyId) {
		SurveyEntity surveyEntity = surveyRepository.findById(surveyId)
			.orElseThrow(() -> new SurveyDoesNotExistException(surveyId));
		return SurveyEntityMapper.toSurvey(surveyEntity);
	}

	@Override
	public Target getTarget(Long targetId) {
		TargetEntity targetEntity = targetRepository.findById(targetId)
			.orElseThrow(() -> new TargetDoesNotExistException(targetId));
		return TargetEntityMapper.toTarget(targetEntity);
	}

	@Override
	public Long getTargetId(Long surveyId) {
		SurveyEntity surveyEntity = surveyRepository.findById(surveyId)
			.orElseThrow(() -> new SurveyDoesNotExistException(surveyId));
		return surveyEntity.getTargetId();
	}
}
