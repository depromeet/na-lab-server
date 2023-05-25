package me.nalab.luffy.api.acceptance.test.feedback;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

public final class FeedbackAcceptanceValidator {

	private FeedbackAcceptanceValidator() {
		throw new UnsupportedOperationException("Cannot invoke constructor \"FeedbackAcceptanceValidator()\"");
	}

	public static void assertIsFeedbackCreated(ResultActions resultActions) throws Exception {
		resultActions.andExpectAll(status().isCreated());
	}

	public static void assertIsReviewerSummarized(ResultActions resultActions) throws Exception {
		resultActions.andExpectAll(
			jsonPath("$.collaboration_experience.yes").isNumber(),
			jsonPath("$.collaboration_experience.no").isNumber(),
			jsonPath("$.position.designer").isNumber(),
			jsonPath("$.position.product-manager").isNumber(),
			jsonPath("$.position.programmer").isNumber(),
			jsonPath("$.position.other").isNumber()
		);
	}

	public static void assertIsFeedbackSummaryFound(ResultActions resultActions) throws Exception {
		resultActions.andExpectAll(
			status().isOk(),
			content().contentType(MediaType.APPLICATION_JSON),
			jsonPath("$.all_feedback_count").isNumber(),
			jsonPath("$.new_feedback_count").isNumber()
		);
	}

}
