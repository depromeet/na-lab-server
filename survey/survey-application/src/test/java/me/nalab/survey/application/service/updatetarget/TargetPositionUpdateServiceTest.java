package me.nalab.survey.application.service.updatetarget;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.nalab.survey.application.exception.TargetDoesNotExistException;
import me.nalab.survey.application.port.out.persistence.target.update.TargetFindPort;
import me.nalab.survey.application.port.out.persistence.target.update.TargetPositionUpdatePort;
import me.nalab.survey.domain.target.Target;

@ExtendWith(SpringExtension.class)
class TargetPositionUpdateServiceTest {

	private TargetPositionUpdateService targetPositionUpdateService;

	@Mock
	private TargetFindPort targetFindPort;

	@Mock
	private TargetPositionUpdatePort targetPositionUpdatePort;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		targetPositionUpdateService = new TargetPositionUpdateService(targetFindPort, targetPositionUpdatePort);
	}

	@Test
	void UPDATE_TARGET_POSITION_SERVICE_SUCCESS_TEST() {

		Long targetId = 123L;
		Target target = Target.builder()
			.id(targetId)
			.nickname("sujin")
			.build();

		when(targetFindPort.findTarget(targetId)).thenReturn(Optional.of(target));
		when(targetPositionUpdatePort.updateTargetPosition(target)).thenReturn(true);

		assertDoesNotThrow(() -> targetPositionUpdateService.updateTargetPosition(targetId, target.getPosition()));

	}

	@Test
	void UPDATE_TARGET_POSITION_SERVICE_FAIL_TEST_CASE_1() {

		Long targetId = 123L;
		Target target = Target.builder()
			.id(targetId)
			.nickname("sujin")
			.build();
		String position = target.getPosition();

		when(targetFindPort.findTarget(targetId)).thenReturn(Optional.of(target));
		when(targetPositionUpdatePort.updateTargetPosition(target)).thenReturn(false);

		assertThrows(TargetDoesNotExistException.class,
			() -> targetPositionUpdateService.updateTargetPosition(targetId, position)
		);

	}

	@Test
	void UPDATE_TARGET_POSITION_SERVICE_FAIL_TEST_CASE_2() {

		Long targetId = 123L;
		Target target = Target.builder()
			.id(targetId)
			.nickname("sujin")
			.build();
		String position = target.getPosition();

		when(targetFindPort.findTarget(targetId)).thenReturn(Optional.empty());
		when(targetPositionUpdatePort.updateTargetPosition(target)).thenReturn(true);

		assertThrows(TargetDoesNotExistException.class,
			() -> targetPositionUpdateService.updateTargetPosition(targetId, position)
		);

	}

	@Test
	void UPDATE_TARGET_POSITION_SERVICE_FAIL_TEST_CASE_3() {

		Long targetId = 123L;
		Target target = Target.builder()
			.id(targetId)
			.nickname("sujin")
			.build();
		String position = target.getPosition();

		when(targetFindPort.findTarget(targetId)).thenReturn(Optional.empty());
		when(targetPositionUpdatePort.updateTargetPosition(target)).thenReturn(false);

		assertThrows(TargetDoesNotExistException.class,
			() -> targetPositionUpdateService.updateTargetPosition(targetId, position)
		);

	}

}
