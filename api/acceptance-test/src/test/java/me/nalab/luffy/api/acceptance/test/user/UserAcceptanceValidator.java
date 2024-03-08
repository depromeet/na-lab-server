package me.nalab.luffy.api.acceptance.test.user;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.ResultActions;

public class UserAcceptanceValidator {

	public static void assertIsLogined(ResultActions resultActions, Long targetId, String nickname, String email) throws Exception {
		resultActions.andExpectAll(
			status().isOk(),
			jsonPath("$.target_id").value(targetId),
			jsonPath("$.nickname").value(nickname),
			jsonPath("$.email").value(email)
		);
	}

	public static void assertIsTargetPositionUpdated(ResultActions resultActions) throws Exception {
		resultActions.andExpectAll(status().isOk());
	}

}
