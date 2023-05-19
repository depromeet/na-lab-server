package me.nalab.survey.jpa.adaptor.common.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import me.nalab.core.data.target.TargetEntity;
import me.nalab.survey.domain.target.Target;

class TargetEntityMapperTest {

	@Test
	public void testToTargetEntity() {
		// Given
		Target target = Target.builder()
			.id(1L)
			.nickname("sujin")
			.build();

		// When
		TargetEntity targetEntity = TargetEntityMapper.toTargetEntity(target);

		// Then
		assertEquals(target.getId(), targetEntity.getId());
		assertEquals(target.getNickname(), targetEntity.getNickname());
	}

	@Test
	public void testToTarget() {
		// Given
		TargetEntity targetEntity = TargetEntity.builder()
			.id(1L)
			.nickname("jun young")
			.build();

		// When
		Target target = TargetEntityMapper.toTarget(targetEntity);

		// Then
		assertEquals(targetEntity.getId(), target.getId());
		assertEquals(targetEntity.getNickname(), target.getNickname());
	}
}
