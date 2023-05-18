package me.nalab.survey.application.service.target.find;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
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
	public void targetFindService_WhenTargetIsFound() {
		// given
		Long targetId = 1L;
		TargetDto expectedTargetDto = TargetDto.builder()
			.id(1L)
			.nickname("sujin")
			.build();
		when(targetFindPort.findTarget(targetId)).thenReturn(expectedTargetDto);

		// when
		TargetDto actualTargetDto = targetFindService.findTarget(targetId);

		// then
		assertEquals(expectedTargetDto, actualTargetDto);
		verify(targetFindPort, times(1)).findTarget(targetId);
		verifyNoMoreInteractions(targetFindPort);
	}

	@Test
	public void targetFindService_WhenTargetIsNotFound() {
		// given
		Long targetId = 1L;
		when(targetFindPort.findTarget(targetId)).thenReturn(null);

		// when
		TargetDto actualTargetDto = targetFindService.findTarget(targetId);

		// then
		assertNull(actualTargetDto);
		verify(targetFindPort, times(1)).findTarget(targetId);
		verifyNoMoreInteractions(targetFindPort);
	}
}
