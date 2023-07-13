package me.nalab.survey.web.adaptor.updatetarget.request;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class TargetPositionUpdateRequest {

	@JsonProperty("position")
	@Size(max = 16, message = "직군은 공백포함 최대 16자까지 입력가능합니다.")
	private String position;

}
