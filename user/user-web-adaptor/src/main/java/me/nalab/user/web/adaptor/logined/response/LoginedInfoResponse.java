package me.nalab.user.web.adaptor.logined.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LoginedInfoResponse {

	@JsonProperty("target_id")
	private final String targetId;
	@JsonProperty("nickname")
	private final String nickName;

}
