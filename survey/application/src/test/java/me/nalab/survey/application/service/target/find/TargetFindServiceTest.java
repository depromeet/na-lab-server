package me.nalab.survey.application.service.target.find;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import me.nalab.survey.application.common.dto.TargetDto;
import me.nalab.survey.application.port.out.persistence.target.find.TargetFindPort;

class TargetFindServiceTest {

	@Mock
	private TargetFindPort targetFindPort;

	private TargetFindService targetFindService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		targetFindService = new TargetFindService(targetFindPort);
	}

	@Test
	void targetFindService() {
		// given
		Long targetId = 1L;
		TargetDto expectedTargetDto = TargetDto.builder()
			.id(1L)
			.nickname("sujin")
			.build();
		when(targetFindPort.getTarget(targetId)).thenReturn(expectedTargetDto);

		// when
		TargetDto actualTargetDto = targetFindService.findTarget(targetId);

		// then
		assertEquals(expectedTargetDto, actualTargetDto);
	}
}
