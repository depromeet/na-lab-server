package me.nalab.survey.application.service.target.find;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import me.nalab.survey.application.common.dto.TargetDto;
import me.nalab.survey.application.common.mapper.TargetDtoMapper;
import me.nalab.survey.application.port.out.persistence.target.find.TargetFindPort;
import me.nalab.survey.domain.target.Target;

class TargetFindServiceTest {

	@Mock
	private TargetFindPort targetFindPort;

	private TargetFindService targetFindService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		targetFindService = new TargetFindService(targetFindPort);
	}

	@Test
	void TARGET_FIND_SERVICE_TEST() {

		Long targetId = 123L;
		Target target = Target.builder()
			.id(targetId)
			.nickname("sujin")
			.build();

		when(targetFindPort.getTarget(targetId)).thenReturn(target);

		TargetDto result = targetFindService.findTarget(targetId);

		assertEquals(TargetDtoMapper.toTargetDto(target), result);

	}
}
