package me.nalab.survey.jpa.adaptor.find;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import me.nalab.core.data.survey.SurveyEntity;
import me.nalab.survey.application.exception.TargetDoesNotHasSurveyException;
import me.nalab.survey.application.port.out.persistence.survey.find.SurveyFindPort;
import me.nalab.survey.domain.survey.Survey;
import me.nalab.survey.jpa.adaptor.common.mapper.SurveyEntityMapper;
import me.nalab.survey.jpa.adaptor.find.repository.SurveyFindRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SurveyFindAdaptor implements SurveyFindPort {

	private final SurveyFindRepository surveyFindRepository;

	@Override
	public Optional<Survey> findSurvey(Long surveyId) {
		Optional<SurveyEntity> surveyEntity = surveyFindRepository.findById(surveyId);
		if(surveyEntity.isEmpty()) {
			return Optional.empty();
		}
		Survey survey = SurveyEntityMapper.toSurvey(surveyEntity.get());
		return Optional.of(survey);
	}


    @Override
    public Survey getSurveyByTargetId(Long targetId) {
        var surveyEntity = surveyFindRepository.findByTargetId(targetId)
			.orElseThrow(() -> new TargetDoesNotHasSurveyException(targetId));

		return SurveyEntityMapper.toSurvey(surveyEntity);
    }
}
