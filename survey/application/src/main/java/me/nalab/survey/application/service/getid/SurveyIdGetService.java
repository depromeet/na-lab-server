package me.nalab.survey.application.service.getid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.exception.TargetDoesNotHasSurveyException;
import me.nalab.survey.application.port.in.web.getid.SurveyIdGetUseCase;
import me.nalab.survey.application.port.out.persistence.findid.SurveyIdFindPort;

@Service
@RequiredArgsConstructor
public class SurveyIdGetService implements SurveyIdGetUseCase {

	private final SurveyIdFindPort surveyIdFindPort;

	@Override
	@Transactional(readOnly = true)
	public Long getSurveyIdByTargetId(Long targetId) {
		return surveyIdFindPort.findSurveyIdByTargetId(targetId)
			.orElseThrow(() -> {
				throw new TargetDoesNotHasSurveyException(targetId);
			});
	}

}
