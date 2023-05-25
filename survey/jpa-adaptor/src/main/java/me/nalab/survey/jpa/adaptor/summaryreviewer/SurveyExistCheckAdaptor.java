package me.nalab.survey.jpa.adaptor.summaryreviewer;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.port.out.persistence.summaryreviewer.SurveyExistCheckPort;
import me.nalab.survey.jpa.adaptor.summaryreviewer.repository.SurveyExistCheckJpaRepository;

@Repository
@RequiredArgsConstructor
public class SurveyExistCheckAdaptor implements SurveyExistCheckPort {

	private final SurveyExistCheckJpaRepository surveyExistCheckJpaRepository;

	@Override
	public boolean isExistSurveyBySurveyId(Long surveyId) {
		return surveyExistCheckJpaRepository.existsById(surveyId);
	}

}
