package me.nalab.survey.application.service.create;

import java.text.MessageFormat;
import lombok.RequiredArgsConstructor;
import me.nalab.core.idgenerator.idcore.IdGenerator;
import me.nalab.survey.application.common.survey.dto.SurveyDto;
import me.nalab.survey.application.common.survey.mapper.SurveyDtoMapper;
import me.nalab.survey.application.exception.DuplicateSurveyException;
import me.nalab.survey.application.exception.TargetDoesNotExistException;
import me.nalab.survey.application.port.in.web.CreateSurveyUseCase;
import me.nalab.survey.application.port.out.persistence.SurveyCreatePort;
import me.nalab.survey.application.port.out.persistence.TargetExistCheckPort;
import me.nalab.survey.application.port.out.persistence.existsurvey.SurveyExistPort;
import me.nalab.survey.domain.survey.Survey;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class SurveyCreateService implements CreateSurveyUseCase {

	private final SurveyCreatePort surveyCreatePort;
    private final SurveyExistPort surveyExistPort;
	private final TargetExistCheckPort targetExistCheckPort;
	private final IdGenerator idGenerator;

	@Override
	@Transactional
	public void createSurvey(Long targetId, SurveyDto surveyDto) {
		throwIfDoesNotExistTarget(targetId);
        if (surveyExistPort.isSurveyExistByTargetId(targetId)) {
            throw new DuplicateSurveyException(MessageFormat.format("이미 Survey를 등록한 Target \"{0}\" 입니다.", targetId));
        }
		Survey survey = SurveyDtoMapper.toSurvey(surveyDto);
		survey.withId(idGenerator::generate);
		surveyCreatePort.createSurvey(targetId, survey);
	}

	private void throwIfDoesNotExistTarget(Long targetId) {
		if(!targetExistCheckPort.isExistTargetByTargetId(targetId)) {
			throw new TargetDoesNotExistException(targetId);
		}
	}

}
