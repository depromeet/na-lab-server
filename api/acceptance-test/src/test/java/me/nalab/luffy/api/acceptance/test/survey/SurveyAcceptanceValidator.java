package me.nalab.luffy.api.acceptance.test.survey;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

public class SurveyAcceptanceValidator {

	public static void assertIsSurveyCreated(ResultActions resultActions) throws Exception {
		resultActions.andExpectAll(
			status().isCreated(),
			content().contentType(MediaType.APPLICATION_JSON),
			jsonPath("$.survey_id").isNumber()
		);
	}

	public static void assertIsSurveyFound(ResultActions resultActions) throws Exception {
		resultActions.andExpectAll(
			status().isOk(),
			content().contentType(MediaType.APPLICATION_JSON),
			jsonPath("$.survey_id").isNumber()
		);
	}

	public static void assertIsTargetDoesNotHasAnySurvey(ResultActions resultActions) throws Exception {
		resultActions.andExpectAll(
			status().isNotFound(),
			content().contentType(MediaType.APPLICATION_JSON),
			jsonPath("$.response_messages").isString()
		);
	}

	public static void assertIsSurveyAndQuestionFound(ResultActions resultActions) throws Exception {
		resultActions.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.target.nickname").value("sujin"))
			.andExpect(jsonPath("$.question_count").value(4))

			.andExpect(jsonPath("$.question[0].type").value("choice"))
			.andExpect(jsonPath("$.question[0].title").value("당신의 포지션을 알려주세요."))
			.andExpect(jsonPath("$.question[0].order").value(3))
			.andExpect(jsonPath("$.question[0].form_type").isString())
			.andExpect(jsonPath("$.question[0].choices[0].order").value(1))
			.andExpect(jsonPath("$.question[0].choices[1].order").value(2))
			.andExpect(jsonPath("$.question[0].choices[2].order").value(3))
			.andExpect(jsonPath("$.question[0].choices[3].order").value(4))


			.andExpect(jsonPath("$.question[1].type").value("choice"))
			.andExpect(jsonPath("$.question[1].title").value("예진님의 성향은 어떤 것이 돋보이나요?"))
			.andExpect(jsonPath("$.question[1].order").value(4))
			.andExpect(jsonPath("$.question[1].choices[0].order").value(1))
			.andExpect(jsonPath("$.question[1].form_type").isString())
			.andExpect(jsonPath("$.question[1].choices[1].order").value(2))
			.andExpect(jsonPath("$.question[1].choices[2].order").value(3))
			.andExpect(jsonPath("$.question[1].choices[3].order").value(4))
			.andExpect(jsonPath("$.question[1].choices[4].order").value(5))
			.andExpect(jsonPath("$.question[1].choices[5].order").value(6))
			.andExpect(jsonPath("$.question[1].choices[6].order").value(7))
			.andExpect(jsonPath("$.question[1].choices[7].order").value(8))

			.andExpect(jsonPath("$.question[2].type").value("short"))
			.andExpect(jsonPath("$.question[2].form_type").isString())
			.andExpect(jsonPath("$.question[2].title").value("당신이 생각하는 예진님의 직무적 강점은 무엇인가요?"))
			.andExpect(jsonPath("$.question[2].order").value(5))

			.andExpect(jsonPath("$.question[3].type").value("short"))
			.andExpect(jsonPath("$.question[3].form_type").isString())
			.andExpect(jsonPath("$.question[3].title").value("당신이 생각하는 예진님의 직무적 약점은 무엇인가요?"))
			.andExpect(jsonPath("$.question[3].order").value(6));
	}

}
