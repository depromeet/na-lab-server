package me.nalab.luffy.api.acceptance.test.feedback;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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

	public static void assertIsFeedbackFound(ResultActions resultActions) throws Exception {
		resultActions.andExpectAll(
			status().isOk(),
			content().contentType(MediaType.APPLICATION_JSON),

			jsonPath("$.question_feedback").isArray(),
			jsonPath("$.question_feedback").isNotEmpty(),
			jsonPath("$.question_feedback.[0].question_id").isNumber(),
			jsonPath("$.question_feedback.[0].order").isNumber(),
			jsonPath("$.question_feedback.[0].type").isString(),
			jsonPath("$.question_feedback.[0].title").isString(),

			jsonPath("$.question_feedback.[0].feedbacks").isArray(),
			jsonPath("$.question_feedback.[0].feedbacks").isNotEmpty(),
			jsonPath("$.question_feedback.[0].feedbacks.[0].feedback_id").isNumber(),
			jsonPath("$.question_feedback.[0].feedbacks.[0].created_at").isString(),
			jsonPath("$.question_feedback.[0].feedbacks.[0].is_read").isBoolean(),

			jsonPath("$.question_feedback.[0].feedbacks.[0].reviewer.reviewer_id").isNumber(),
			jsonPath("$.question_feedback.[0].feedbacks.[0].reviewer.nickname").isString(),
			jsonPath("$.question_feedback.[0].feedbacks.[0].reviewer.collaboration_experience").isBoolean(),
			jsonPath("$.question_feedback.[0].feedbacks.[0].reviewer.position").isString()
		);
	}

	public static void assertIsFeedbackNotFound(ResultActions resultActions) throws Exception {
		resultActions.andExpectAll(
			status().isOk(),
			content().contentType(MediaType.APPLICATION_JSON),

			jsonPath("$.question_feedback").isArray(),
			jsonPath("$.question_feedback").isEmpty()
		);
	}

}
