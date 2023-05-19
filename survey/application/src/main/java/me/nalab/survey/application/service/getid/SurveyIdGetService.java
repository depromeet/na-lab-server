package me.nalab.survey.application.service.getid;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.exception.EmptySurveyIdListException;
import me.nalab.survey.application.port.in.web.getid.SurveyIdGetUseCase;
import me.nalab.survey.application.port.out.persistence.findid.SurveyIdFindPort;

@Service
@RequiredArgsConstructor
public class SurveyIdGetService implements SurveyIdGetUseCase {

	private final SurveyIdFindPort surveyIdFindPort;

	@Override
	@Transactional(readOnly = true)
	public List<Long> getSurveyIdByTargetId(Long targetId) {
		List<Long> surveyIdList = surveyIdFindPort.findSurveyIdByTargetId(targetId);
		if(surveyIdList == null || surveyIdList.isEmpty()){
			throw new EmptySurveyIdListException(targetId);
		}
		return surveyIdList;
	}

}
