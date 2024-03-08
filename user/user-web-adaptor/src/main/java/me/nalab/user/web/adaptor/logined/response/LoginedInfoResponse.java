package me.nalab.user.web.adaptor.logined.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import me.nalab.user.application.common.dto.LoginedInfo;

@Data
public class LoginedInfoResponse {

	@JsonProperty("target_id")
	private final String targetId;
	@JsonProperty("nickname")
	private final String nickName;
	@JsonProperty("email")
	private final String email;

	public static LoginedInfoResponse of(LoginedInfo loginedInfo) {
		return new LoginedInfoResponse(String.valueOf(loginedInfo.targetId()), loginedInfo.nickname(), loginedInfo.email());
	}

}
