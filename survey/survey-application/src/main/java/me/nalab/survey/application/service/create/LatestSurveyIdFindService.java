package me.nalab.survey.application.service.create;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.port.in.web.LatestSurveyIdFindUseCase;
import me.nalab.survey.application.port.out.persistence.LatestSurveyIdFindPort;

@Service
@RequiredArgsConstructor
public class LatestSurveyIdFindService implements LatestSurveyIdFindUseCase {

	private final LatestSurveyIdFindPort latestSurveyIdFindPort;

	@Override
	@Transactional(readOnly = true)
	public Long getLatestSurveyIdByTargetId(Long targetId) {
		return latestSurveyIdFindPort.findLatestSurveyIdByTargetId(targetId)
			.orElseThrow(() -> {
				throw new IllegalStateException(
					"Cannot find any survey. \"This method must be called after one or more surveys have been created.\""
				);
			});
	}

}
