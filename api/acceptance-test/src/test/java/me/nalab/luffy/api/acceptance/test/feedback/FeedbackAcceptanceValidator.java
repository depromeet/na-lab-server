package me.nalab.luffy.api.acceptance.test.feedback;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
			jsonPath("$.position.pm").isNumber(),
			jsonPath("$.position.developer").isNumber(),
			jsonPath("$.position.others").isNumber()
		);
	}

	public static void assertIsFeedbackFound(ResultActions resultActions) throws Exception {
		resultActions.andExpectAll(
			status().isOk(),
			content().contentType(MediaType.APPLICATION_JSON),

			jsonPath("$.question_feedback").isArray(),
			jsonPath("$.question_feedback").isNotEmpty(),
			jsonPath("$.question_feedback.[0].question_id").isString(),
			jsonPath("$.question_feedback.[0].order").isNumber(),
			jsonPath("$.question_feedback.[0].type").isString(),
			jsonPath("$.question_feedback.[0].form_type").isString(),
			jsonPath("$.question_feedback.[0].title").isString(),

			jsonPath("$.question_feedback.[0].feedbacks").isArray(),
			jsonPath("$.question_feedback.[0].feedbacks").isNotEmpty(),
			jsonPath("$.question_feedback.[0].feedbacks.[0].feedback_id").isString(),
			jsonPath("$.question_feedback.[0].feedbacks.[0].form_question_feedback_id").isString(),
			jsonPath("$.question_feedback.[0].feedbacks.[0].created_at").isString(),
			jsonPath("$.question_feedback.[0].feedbacks.[0].is_read").isBoolean(),
			jsonPath("$.question_feedback.[0].feedbacks.[0].bookmark").isNotEmpty(),
			jsonPath("$.question_feedback.[0].feedbacks.[0].bookmark.is_bookmarked").isBoolean(),
			jsonPath("$.question_feedback.[0].feedbacks.[0].bookmark.bookmarked_at").isString(),

			jsonPath("$.question_feedback.[0].feedbacks.[0].reviewer.reviewer_id").isString(),
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
			jsonPath("$.question_feedback").isNotEmpty(),

			jsonPath("$.question_feedback.[0].question_id").isString(),
			jsonPath("$.question_feedback.[0].order").isNumber(),
			jsonPath("$.question_feedback.[0].type").isString(),
			jsonPath("$.question_feedback.[0].form_type").isString(),
			jsonPath("$.question_feedback.[0].title").isString(),

			jsonPath("$.question_feedback.[0].feedbacks").isArray(),
			jsonPath("$.question_feedback.[0].feedbacks").isEmpty()
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

	public static void assertIsReviewersNotFound(ResultActions resultActions) throws Exception {
		resultActions.andExpectAll(
			status().isOk(),
			content().contentType(MediaType.APPLICATION_JSON),
			jsonPath("$.feedbacks").isArray(),
			jsonPath("$.feedbacks").isEmpty()
		);
	}

	public static void assertIsReviewersFound(ResultActions resultActions) throws Exception {
		resultActions.andExpectAll(
			status().isOk(),
			content().contentType(MediaType.APPLICATION_JSON),
			jsonPath("$.feedbacks").isArray(),
			jsonPath("$.feedbacks[0].feedback_id").isString(),
			jsonPath("$.feedbacks[0].created_at").isString(),
			jsonPath("$.feedbacks[0].reviewer.nickname").isString(),
			jsonPath("$.feedbacks[0].reviewer.collaboration_experience").isBoolean(),
			jsonPath("$.feedbacks[0].reviewer.position").isString(),
			jsonPath("$.feedbacks[0].is_read").isBoolean()
		);
	}

	public static void assertIsSpecificFound(ResultActions resultActions) throws Exception {
		resultActions.andExpectAll(
			status().isOk(),
			content().contentType(MediaType.APPLICATION_JSON),
			jsonPath("$.feedback_id").isString(),
			jsonPath("$.created_at").isString(),
			jsonPath("$.reviewer.nickname").isString(),
			jsonPath("$.reviewer.collaboration_experience").isBoolean(),
			jsonPath("$.reviewer.position").isString(),
			jsonPath("$.question").isArray(),
			jsonPath("$.question[0].question_id").isString(),
			jsonPath("$.question[0].type").isString(),
			jsonPath("$.question[0].form_type").isString(),
			jsonPath("$.question[0].title").isString(),
			jsonPath("$.question[0].order").isNumber(),
			jsonPath("$.question[0].is_read").isBoolean()
		);
	}

	public static void assertIsBookmarkReplaced(ResultActions resultActions) throws Exception {
		resultActions.andExpectAll(status().isOk());
	}

	public static void assertIsFeedbackFoundByTendency(ResultActions resultActions) throws Exception {
		resultActions.andExpectAll(
			status().isOk(),
			content().contentType(MediaType.APPLICATION_JSON),

			jsonPath("$.question_feedback").isArray(),
			jsonPath("$.question_feedback").isNotEmpty(),

			jsonPath("$.question_feedback.[0].question_id").isString(),
			jsonPath("$.question_feedback.[0].order").isNumber(),
			jsonPath("$.question_feedback.[0].type").isString(),
			jsonPath("$.question_feedback.[0].form_type").value("tendency"),
			jsonPath("$.question_feedback.[0].title").isString(),
			jsonPath("$.question_feedback.[0].choices").isArray()
		);
	}

	public static void assertIsFeedbackFoundByCustom(ResultActions resultActions) throws Exception {
		resultActions.andExpectAll(
			status().isOk(),
			content().contentType(MediaType.APPLICATION_JSON),

			jsonPath("$.question_feedback").isArray(),
			jsonPath("$.question_feedback").isNotEmpty(),

			jsonPath("$.question_feedback.[0].question_id").isString(),
			jsonPath("$.question_feedback.[0].order").isNumber(),
			jsonPath("$.question_feedback.[0].type").isString(),
			jsonPath("$.question_feedback.[0].form_type").value("custom"),
			jsonPath("$.question_feedback.[0].title").isString()
		);
	}

	public static void assertIsFeedbackFoundByFormTypeWithNoFeedback(ResultActions resultActions) throws Exception {
		resultActions.andExpectAll(
			status().isOk(),
			content().contentType(MediaType.APPLICATION_JSON),

			jsonPath("$.question_feedback").isArray(),
			jsonPath("$.question_feedback").isNotEmpty(),

			jsonPath("$.question_feedback.[0].question_id").isString(),
			jsonPath("$.question_feedback.[0].order").isNumber(),
			jsonPath("$.question_feedback.[0].type").isString(),
			jsonPath("$.question_feedback.[0].form_type").isString(),
			jsonPath("$.question_feedback.[0].title").isString()
		);
	}

}
