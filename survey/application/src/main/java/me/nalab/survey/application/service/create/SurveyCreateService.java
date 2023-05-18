package me.nalab.survey.application.service.create;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import me.nalab.core.idgenerator.idcore.IdGenerator;
import me.nalab.survey.application.common.dto.SurveyDto;
import me.nalab.survey.application.common.mapper.SurveyDtoMapper;
import me.nalab.survey.application.exception.TargetDoesNotExistException;
import me.nalab.survey.application.port.in.web.CreateSurveyUseCase;
import me.nalab.survey.application.port.in.web.LatestSurveyIdFindUseCase;
import me.nalab.survey.application.port.out.persistence.LatestSurveyIdFindPort;
import me.nalab.survey.application.port.out.persistence.SurveyCreatePort;
import me.nalab.survey.application.port.out.persistence.TargetExistCheckPort;
import me.nalab.survey.domain.survey.Survey;

@Service
@RequiredArgsConstructor
class SurveyCreateService implements CreateSurveyUseCase, LatestSurveyIdFindUseCase {

	private final SurveyCreatePort surveyCreatePort;
	private final TargetExistCheckPort targetExistCheckPort;
	private final LatestSurveyIdFindPort latestSurveyIdFindPort;
	private final IdGenerator idGenerator;

	@Override
	@Transactional
	public void createSurvey(Long targetId, SurveyDto surveyDto) {
		throwIfDoesNotExistTarget(targetId);
		Survey survey = SurveyDtoMapper.toSurvey(surveyDto);
		survey.withId(idGenerator::generate);
		surveyCreatePort.persistenceSurvey(targetId, survey);
	}

	private void throwIfDoesNotExistTarget(Long targetId) {
		if(!targetExistCheckPort.isExistTarget(targetId)) {
			throw new TargetDoesNotExistException(targetId);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Long findLatestCreatedSurveyId(Long targetId) {
		return latestSurveyIdFindPort.findLatestCreatedSurveyId(targetId);
	}

}
