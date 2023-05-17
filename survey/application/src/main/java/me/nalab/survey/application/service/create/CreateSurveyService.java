package me.nalab.survey.application.service.create;

import org.springframework.stereotype.Service;

import me.nalab.core.idgenerator.idcore.IdGenerator;
import me.nalab.survey.application.common.dto.SurveyDto;
import me.nalab.survey.application.common.mapper.SurveyDtoMapper;
import me.nalab.survey.application.exception.TargetDoesNotExistException;
import me.nalab.survey.application.port.in.web.CreateSurveyUseCase;
import me.nalab.survey.application.port.out.persistence.CreateSurveyPort;
import me.nalab.survey.application.port.out.persistence.FindTargetPort;
import me.nalab.survey.domain.survey.Survey;

@Service
class CreateSurveyService implements CreateSurveyUseCase {

	private final CreateSurveyPort createSurveyPort;
	private final FindTargetPort findTargetPort;
	private final IdGenerator idGenerator;

	CreateSurveyService(CreateSurveyPort createSurveyPort, FindTargetPort findTargetPort, IdGenerator idGenerator) {
		this.createSurveyPort = createSurveyPort;
		this.findTargetPort = findTargetPort;
		this.idGenerator = idGenerator;
	}

	@Override
	public void createSurvey(Long targetId, SurveyDto surveyDto) {
		throwIfDoesNotExistTarget(targetId);
		Survey survey = SurveyDtoMapper.toSurvey(surveyDto);
		survey.withId(idGenerator::generate);
		createSurveyPort.persistenceSurvey(targetId, survey);
	}

	private void throwIfDoesNotExistTarget(Long targetId) {
		if(!findTargetPort.isExistTarget(targetId)) {
			throw new TargetDoesNotExistException(targetId);
		}
	}

}
