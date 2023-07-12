package me.nalab.survey.web.adaptor.findtarget;

import me.nalab.survey.application.common.survey.dto.TargetDto;
import me.nalab.survey.web.adaptor.findtarget.response.TargetResponse;

class TargetResponseMapper {

	private TargetResponseMapper() {
		throw new UnsupportedOperationException("Cannot invoke constructor \"TargetResponseMapper()\"");
	}

	static TargetResponse toTargetResponse(TargetDto targetDto) {
		return TargetResponse.builder()
			.targetId(targetDto.getId())
			.nickname(targetDto.getNickname())
			.position(targetDto.getPosition())
			.build();
	}

}
