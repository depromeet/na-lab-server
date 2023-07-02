package me.nalab.survey.application.service.getid;

import java.util.Collections;
import java.util.List;

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
		List<Long> surveyIdList = surveyIdFindPort.findAllSurveyIdByTargetId(targetId);
		if(surveyIdList == null || surveyIdList.isEmpty()) {
			throw new TargetDoesNotHasSurveyException(targetId);
		}
		Collections.sort(surveyIdList);
		return surveyIdList.get(surveyIdList.size() - 1);
	}

}
