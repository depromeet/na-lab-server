package me.nalab.survey.jpa.adaptor.findfeedbacksummary;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.port.out.persistence.feedbacksummary.SurveyExistCheckPort;
import me.nalab.survey.jpa.adaptor.findfeedbacksummary.repository.SurveyExistCheckJpaRepository;

@Repository("findfeedbacksummary.SurveyExistCheckAdaptor")
@RequiredArgsConstructor
public class SurveyExistCheckAdaptor implements SurveyExistCheckPort {

	private final SurveyExistCheckJpaRepository surveyExistCheckJpaRepository;

	@Override
	public boolean isExistSurveyBySurveyId(Long surveyId) {
		return surveyExistCheckJpaRepository.existsById(surveyId);
	}
}
