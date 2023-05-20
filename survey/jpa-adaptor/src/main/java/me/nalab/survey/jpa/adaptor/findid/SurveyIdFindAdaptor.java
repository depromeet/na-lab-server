package me.nalab.survey.jpa.adaptor.findid;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.port.out.persistence.findid.SurveyIdFindPort;
import me.nalab.survey.jpa.adaptor.findid.repository.SurveyIdFindJpaRepository;

@Repository
@RequiredArgsConstructor
public class SurveyIdFindAdaptor implements SurveyIdFindPort {

	private final SurveyIdFindJpaRepository surveyIdFindJpaRepository;

	@Override
	public List<Long> findAllSurveyIdByTargetId(Long targetId) {
		return surveyIdFindJpaRepository.findAllIdByTargetId(targetId);
	}

}
