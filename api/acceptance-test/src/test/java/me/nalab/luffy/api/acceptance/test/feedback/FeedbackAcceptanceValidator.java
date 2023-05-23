package me.nalab.luffy.api.acceptance.test.feedback;

import org.springframework.test.web.servlet.ResultActions;

public final class FeedbackAcceptanceValidator {

	private FeedbackAcceptanceValidator() {
		throw new UnsupportedOperationException("Cannot invoke constructor \"FeedbackAcceptanceValidator()\"");
	}

	//:TODO 여기에 Feedback 인수테스트 validator 로직을 static으로 작성 해주세요. SurveyAcceptanceValidator 참고해서 작성하면 됩니다.

	//: TODO
	public static void assertIsFeedbackSummaryFound(ResultActions resultActions) throws Exception {
		resultActions.andExpectAll(
			status().isOk(),
			content().contentType(MediaType.APPLICATION_JSON),
			jsonPath("$.all_feedback_count").isNumber(),
			jsonPath("$.new_feedback_count").isNumber()
		);
	}
}
