package me.nalab.luffy.api.acceptance.test.user.updatetarget.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TargetPositionUpdateRequest {

	@JsonProperty("position")
	private String position;

}
