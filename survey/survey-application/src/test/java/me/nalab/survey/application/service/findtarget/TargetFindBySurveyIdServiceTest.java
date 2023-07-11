package me.nalab.survey.application.service.findtarget;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.nalab.survey.application.common.survey.dto.TargetDto;
import me.nalab.survey.application.common.survey.mapper.TargetDtoMapper;
import me.nalab.survey.application.exception.SurveyDoesNotHasTargetException;
import me.nalab.survey.application.exception.TargetDoesNotExistException;
import me.nalab.survey.application.port.out.persistence.findtarget.TargetFindPort;
import me.nalab.survey.application.port.out.persistence.findtarget.TargetIdFindPort;
import me.nalab.survey.domain.target.Target;

@ExtendWith(SpringExtension.class)
class TargetFindBySurveyIdServiceTest {

	private TargetFindBySurveyIdService targetFindBySurveyIdService;

	@Mock
	private TargetIdFindPort targetIdFindPort;

	@Mock
	private TargetFindPort targetFindPort;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		targetFindBySurveyIdService = new TargetFindBySurveyIdService(targetFindPort, targetIdFindPort);
	}

	@Test
	void FIND_TARGET_BY_SURVEY_ID_SUCCESS_TEST() {

		Long targetId = 123L;
		Long surveyId = 12L;
		Target target = Target.builder()
			.id(targetId)
			.nickname("sujin")
			.build();

		when(targetIdFindPort.findTargetIdBySurveyId(surveyId)).thenReturn(Optional.of(targetId));
		when(targetFindPort.findTarget(targetId)).thenReturn(Optional.of(target));
		TargetDto targetDto = targetFindBySurveyIdService.findTargetBySurveyId(surveyId);

		assertEquals(TargetDtoMapper.toTarget(targetDto), target);
	}

	@Test
	void FIND_TARGET_BY_SURVEY_ID_FAILED_TEST_WITH_NO_TARGET_ID() {

		Long targetId = 123L;
		Long surveyId = 12L;
		Target target = Target.builder()
			.id(targetId)
			.nickname("sujin")
			.build();

		when(targetIdFindPort.findTargetIdBySurveyId(surveyId)).thenReturn(Optional.empty());
		when(targetFindPort.findTarget(targetId)).thenReturn(Optional.of(target));

		assertThrows(SurveyDoesNotHasTargetException.class,
			() -> targetFindBySurveyIdService.findTargetBySurveyId(surveyId));

	}

	@Test
	void FIND_TARGET_BY_SURVEY_ID_FAILED_TEST_WITH_NO_TARGET() {

		Long targetId = 123L;
		Long surveyId = 12L;
		Target target = Target.builder()
			.id(targetId)
			.nickname("sujin")
			.build();

		when(targetIdFindPort.findTargetIdBySurveyId(surveyId)).thenReturn(Optional.of(targetId));
		when(targetFindPort.findTarget(targetId)).thenReturn(Optional.empty());

		assertThrows(TargetDoesNotExistException.class,
			() -> targetFindBySurveyIdService.findTargetBySurveyId(surveyId));

	}

}
