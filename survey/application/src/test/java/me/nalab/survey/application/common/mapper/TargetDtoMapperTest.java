package me.nalab.survey.application.common.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import me.nalab.survey.application.common.dto.TargetDto;
import me.nalab.survey.domain.target.Target;

class TargetDtoMapperTest {

	@Test
	void ToTarget() {
		// Given
		TargetDto targetDto = TargetDto.builder()
			.id(1L)
			.nickname("sujin")
			.build();

		// When
		Target actualTarget = TargetDtoMapper.toTarget(targetDto);

		// Then
		assertEquals(targetDto.getId(), actualTarget.getId());
		assertEquals(targetDto.getNickname(), actualTarget.getNickname());
	}

	@Test
	void testToTargetDto() {
		// Given
		Target target = Target.builder()
			.id(1L)
			.nickname("sujin")
			.build();

		// When
		TargetDto actualTargetDto = TargetDtoMapper.toTargetDto(target);

		// Then
		assertEquals(target.getId(), actualTargetDto.getId());
		assertEquals(target.getNickname(), actualTargetDto.getNickname());
	}

}
