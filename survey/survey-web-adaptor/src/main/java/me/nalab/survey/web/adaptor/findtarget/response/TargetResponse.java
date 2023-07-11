package me.nalab.survey.web.adaptor.findtarget.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class TargetResponse {

	@JsonProperty("target_id")
	private final long targetId;

	@JsonProperty("nickname")
	private final String nickname;

	@JsonProperty("position")
	private final String position;

}
