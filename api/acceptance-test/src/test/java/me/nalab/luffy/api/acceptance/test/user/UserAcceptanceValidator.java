package me.nalab.luffy.api.acceptance.test.user;

import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserAcceptanceValidator {

	public static void assertIsLogined(ResultActions resultActions, Long targetId, String nickname) throws Exception{
		resultActions.andExpectAll(
			status().isOk(),
			jsonPath("$.target_id").value(targetId),
			jsonPath("$.nickname").value(nickname)
		);
	}

}
