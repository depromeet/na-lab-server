package me.nalab.survey.jpa.adaptor.common.mapper;

import me.nalab.core.data.target.TargetEntity;
import me.nalab.survey.domain.target.Target;

public class TargetEntityMapper {

	private TargetEntityMapper() {
		throw new UnsupportedOperationException("Cannot invoke constructor \"TargetEntityMapper()\"");
	}

	public static TargetEntity toTargetEntity(Target target) {
		return TargetEntity.builder()
			.id(target.getId())
			.nickname(target.getNickname())
			.build();
	}

	public static Target toTarget(TargetEntity targetEntity) {
		return Target.builder()
			.id(targetEntity.getId())
			.nickname(targetEntity.getNickname())
			.build();
	}
}
