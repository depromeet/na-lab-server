package me.nalab.survey.jpa.adaptor.authorization;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.port.out.persistence.authorization.TargetIdFindPort;
import me.nalab.survey.jpa.adaptor.authorization.repository.TargetIdFindJpaRepository;

@Repository
@RequiredArgsConstructor
public class TargetIdFindAdaptor implements TargetIdFindPort {

	private final TargetIdFindJpaRepository targetIdFindJpaRepository;

	@Override
	public Optional<Long> findTargetIdBySurveyId(Long surveyId) {
		return targetIdFindJpaRepository.findTargetIdBySurveyId(surveyId);
	}

	@Override
	public Optional<Long> findTargetIdByFeedbackId(Long feedbackId) {
		return targetIdFindJpaRepository.findTargetIdByFeedbackId(feedbackId);
	}

}
