package me.nalab.api.mock;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/mock", produces = MediaType.APPLICATION_JSON_VALUE)
public class MockController {

	@PostMapping("/feedbacks")
	@ResponseStatus(HttpStatus.CREATED)
	void createFeedback(@RequestParam(name = "survey-id", required = false) Long surveyId,
		@Validated @RequestBody(required = false) Object feedbackCreateRequest) {
	}

	@PostMapping("/surveys")
	@ResponseStatus(HttpStatus.CREATED)
	Object createSurvey(@RequestAttribute(name = "logined", required = false) Long loginId,
		@RequestBody(required = false) Object surveyCreateRequest) {
		return "{\n"
			+ "    \"survey_id\": \"1238413841\"\n"
			+ "}";
	}

	@GetMapping("/reviewers")
	@ResponseStatus(HttpStatus.OK)
	Object getFeedbackReviewers(@RequestParam(name = "survey-id", required = false) Long surveyId) {
		return "{\n"
			+ "    \"feedbacks\": [\n"
			+ "        {\n"
			+ "            \"feedback_id\": 5,\n"
			+ "            \"created_at\": \"2023-01-24T12:00:00\",\n"
			+ "            \"reviwer\": {\n"
			+ "                \"nickname\": \"A\",\n"
			+ "                \"collaboration_experience\": true,\n"
			+ "                \"position\": \"developer\"\n"
			+ "            },\n"
			+ "            \"is_read\": false\n"
			+ "        },\n"
			+ "        {\n"
			+ "            \"feedback_id\": 4,\n"
			+ "            \"created_at\": \"2023-01-23T12:00:00\",\n"
			+ "            \"reviwer\": {\n"
			+ "                \"nickname\": \"B\",\n"
			+ "                \"collaboration_experience\": true,\n"
			+ "                \"position\": \"designer\"\n"
			+ "            },\n"
			+ "            \"is_read\": false\n"
			+ "        },\n"
			+ "        {\n"
			+ "            \"feedback_id\": 3,\n"
			+ "            \"created_at\": \"2023-01-22T12:00:00\",\n"
			+ "            \"reviwer\": {\n"
			+ "                \"nickname\": \"C\",\n"
			+ "                \"collaboration_experience\": false,\n"
			+ "                \"position\": \"designer\"\n"
			+ "            },\n"
			+ "            \"is_read\": true\n"
			+ "        },\n"
			+ "        {\n"
			+ "            \"feedback_id\": 2,\n"
			+ "            \"created_at\": \"2023-01-21T12:00:00\",\n"
			+ "            \"reviwer\": {\n"
			+ "                \"nickname\": \"D\",\n"
			+ "                \"collaboration_experience\": false,\n"
			+ "                \"position\": \"developer\"\n"
			+ "            },\n"
			+ "            \"is_read\": true\n"
			+ "        },\n"
			+ "        {\n"
			+ "            \"feedback_id\": 1,\n"
			+ "            \"created_at\": \"2023-01-20T12:00:00\",\n"
			+ "            \"reviwer\": {\n"
			+ "                \"nickname\": \"E\",\n"
			+ "                \"collaboration_experience\": false,\n"
			+ "                \"position\": \"developer\"\n"
			+ "            },\n"
			+ "            \"is_read\": true\n"
			+ "        }\n"
			+ "    ]\n"
			+ "}";
	}

	@GetMapping("/feedbacks/summary")
	@ResponseStatus(HttpStatus.OK)
	Object getFeedbackSummary(@RequestParam(name = "survey-id", required = false) Long surveyId) {
		return "{\n"
			+ "    \"all_feedback_count\": 6,\n"
			+ "    \"new_feedback_count\": 1\n"
			+ "}";
	}

	@GetMapping("/surveys-id")
	@ResponseStatus(HttpStatus.OK)
	Object getSurveyId(@RequestAttribute(name = "logined", required = false) Long loginId) {
		return "{\n"
			+ "    \"survey_id\": 1384314132443\n"
			+ "}";
	}

	@GetMapping("/feedbacks/{feedback-id}")
	@ResponseStatus(HttpStatus.OK)
	Object getSpecificFeedback(@PathVariable("feedback-id") Long feedbackId) {
		return "{\n"
			+ "    \"feedback_id\": 5,\n"
			+ "    \"created_at\": \"2023-01-24T12:00:00\",\n"
			+ "    \"reviewer\": {\n"
			+ "        \"nickname\": \"A\",\n"
			+ "        \"collaboration_experience\": true,\n"
			+ "        \"position\": \"developer\"\n"
			+ "    },\n"
			+ "    \"question\": [\n"
			+ "        {\n"
			+ "            \"question_id\": 1,\n"
			+ "            \"type\": \"choice\",\n"
			+ "            \"form_type\": \"tendency\",\n"
			+ "            \"title\": \"성향 예시\",\n"
			+ "            \"order\": 4,\n"
			+ "            \"is_read\": false,\n"
			+ "            \"choices\": [\n"
			+ "                {\n"
			+ "                    \"choice_id\": 3,\n"
			+ "                    \"content\": \"UI\",\n"
			+ "                    \"order\": 1\n"
			+ "                },\n"
			+ "                {\n"
			+ "                    \"choice_id\": 4,\n"
			+ "                    \"content\": \"UI\",\n"
			+ "                    \"order\": 2\n"
			+ "                }\n"
			+ "            ]\n"
			+ "        },\n"
			+ "        {\n"
			+ "            \"question_id\": 10,\n"
			+ "            \"type\": \"short\",\n"
			+ "            \"form_type\": \"strength\",\n"
			+ "            \"title\": \"저는 UX, UI, GUI 중에 어떤 분야에 더 강점이 있나요?\",\n"
			+ "            \"order\": 5,\n"
			+ "            \"is_read\": false,\n"
			+ "            \"reply\": [\n"
			+ "                \"예진이는 개발 관련 지식을 조금 더 공부해봐도\\n좋을 것 같아! 요즘 프로덕트 디자이너에겐 개발\\n지식을 아는 게 좋은 역량이 될 수 있어.\",\n"
			+ "                \"안녕\"\n"
			+ "            ]\n"
			+ "        }\n"
			+ "    ]\n"
			+ "}";
	}

	@GetMapping("/reviewers/summary")
	@ResponseStatus(HttpStatus.OK)
	Object getReviewerSummary(@RequestParam(name = "survey-id", required = false) Long surveyId) {
		return "{\n"
			+ "    \"collaboration_experience\": {\n"
			+ "        \"yes\": 1,\n"
			+ "        \"no\": 4\n"
			+ "    },\n"
			+ "    \"position\": {\n"
			+ "        \"developer\": 2,\n"
			+ "        \"pm\": 3,\n"
			+ "        \"designer\": 2,\n"
			+ "        \"others\": 1\n"
			+ "    }\n"
			+ "}";
	}

	@GetMapping("/surveys/{surveyId}")
	@ResponseStatus(HttpStatus.OK)
	Object getSurvey(@PathVariable(name = "surveyId", required = false) Long surveyId) {
		return "{\n"
			+ "    \"survey_id\": 1,\n"
			+ "    \"target\": {\n"
			+ "        \"id\": 26,\n"
			+ "        \"nickname\": \"예진\"\n"
			+ "    },\n"
			+ "    \"question_count\": 2,\n"
			+ "    \"question\": [\n"
			+ "        {\n"
			+ "            \"question_id\": 1,\n"
			+ "            \"type\": \"choice\",\n"
			+ "            \"form_type\": \"tendency\",\n"
			+ "            \"title\": \"성향~\",\n"
			+ "            \"order\": 1,\n"
			+ "            \"max_selectable_count\": 5,\n"
			+ "            \"choices\": [\n"
			+ "                {\n"
			+ "                    \"choice_id\": 3,\n"
			+ "                    \"content\": \"꼼꼼한1\",\n"
			+ "                    \"order\": 1\n"
			+ "                },\n"
			+ "                {\n"
			+ "                    \"choice_id\": 4,\n"
			+ "                    \"content\": \"꼼꼼한2\",\n"
			+ "                    \"order\": 2\n"
			+ "                },\n"
			+ "                {\n"
			+ "                    \"choice_id\": 3,\n"
			+ "                    \"content\": \"꼼꼼한3\",\n"
			+ "                    \"order\": 1\n"
			+ "                },\n"
			+ "                {\n"
			+ "                    \"choice_id\": 3,\n"
			+ "                    \"content\": \"꼼꼼한4\",\n"
			+ "                    \"order\": 1\n"
			+ "                },\n"
			+ "                {\n"
			+ "                    \"choice_id\": 3,\n"
			+ "                    \"content\": \"꼼꼼한5\",\n"
			+ "                    \"order\": 1\n"
			+ "                },\n"
			+ "                {\n"
			+ "                    \"choice_id\": 3,\n"
			+ "                    \"content\": \"꼼꼼한6\",\n"
			+ "                    \"order\": 1\n"
			+ "                },\n"
			+ "                {\n"
			+ "                    \"choice_id\": 3,\n"
			+ "                    \"content\": \"꼼꼼한7\",\n"
			+ "                    \"order\": 1\n"
			+ "                }\n"
			+ "            ]\n"
			+ "        },\n"
			+ "        {\n"
			+ "            \"question_id\": 1,\n"
			+ "            \"type\": \"choice\",\n"
			+ "            \"form_type\": \"custom\",\n"
			+ "            \"title\": \"저는 UI, UI, GUI 중에 어떤 분야를 가장 잘하는 것 같나요?\",\n"
			+ "            \"order\": 4,\n"
			+ "            \"max_selectable_count\": 1,\n"
			+ "            \"choices\": [\n"
			+ "                {\n"
			+ "                    \"choice_id\": 3,\n"
			+ "                    \"content\": \"UI\",\n"
			+ "                    \"order\": 1\n"
			+ "                },\n"
			+ "                {\n"
			+ "                    \"choice_id\": 4,\n"
			+ "                    \"content\": \"UX\",\n"
			+ "                    \"order\": 2\n"
			+ "                }\n"
			+ "            ]\n"
			+ "        },\n"
			+ "        {\n"
			+ "            \"question_id\": 10,\n"
			+ "            \"type\": \"short\",\n"
			+ "            \"form_type\": \"strength\",\n"
			+ "            \"title\": \"저는 UX, UI, GUI 중에 어떤 분야에 더 강점이 있나요?\",\n"
			+ "            \"order\": 5\n"
			+ "        }\n"
			+ "    ]\n"
			+ "}";
	}

	@GetMapping("/users/logined")
	@ResponseStatus(HttpStatus.OK)
	Object getLoginedUser() {
		return "{\n"
			+ "    \"target_id\": 26,\n"
			+ "    \"nickname\": \"예진\"\n"
			+ "}";
	}

	@GetMapping("/oauth/{oauth-provider}")
	Object loginWithOauth(@PathVariable(name = "oauth-provider", required = false) String oauthProvider) {
		return "{\n"
			+ "    \"token_type\": \"bearer\",\n"
			+ "    \"access_token\": \"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjQ1NTEwMjE1LCJpYXQiOjE2NDU1MDk5MTUsImp0aSI6IjExYTEyNmE1OWZlNzQ5ZmI5YmU1MDQwOGUxOWNkNGU4IiwidXNlcl9pZCI6Mn0.Jpr1gg76_LrjXbWrc_y_ohstacwrgJj1p5jNFJWdyr4\"\n"
			+ "}";
	}

	@PatchMapping("/users")
	@ResponseStatus(HttpStatus.OK)
	void updateTarget() {
		// 200 OK 반환용 mock api 메소드로 비어있음
	}

	@GetMapping("/users")
	@ResponseStatus(HttpStatus.OK)
	Object getTargetBySurveyId(@RequestParam("survey-id") Long surveyId) {
		return "{\n"
			+ "    \"target_id\": 26,\n"
			+ "    \"nickname\": \"예진\",\n"
			+ "    \"position\": \"개발자\"\n"
			+ "}";
	}

	@GetMapping("/feedbacks")
	@ResponseStatus(HttpStatus.OK)
	Object getFeedbackBySurveyIdAndFormType(@RequestParam("survey-id") Long surveyId,
		@RequestParam(value = "form-type", required = false) String formType) {
		if (formType == null || formType.isBlank()) {
			return "{\n"
				+ "    \"question_feedback\": [\n"
				+ "        {\n"
				+ "            \"question_id\": 1,\n"
				+ "            \"order\": 1,\n"
				+ "            \"type\": \"choice\",\n"
				+ "            \"form_type\": \"tendency\",\n"
				+ "            \"title\": \"성향\",\n"
				+ "            \"choices\": [\n"
				+ "                {\n"
				+ "                    \"choice_id\": \"1\",\n"
				+ "                    \"order\": 1,\n"
				+ "                    \"content\": \"꼼꼼한\"\n"
				+ "                },\n"
				+ "                {\n"
				+ "                    \"choice_id\": \"2\",\n"
				+ "                    \"order\": 2,\n"
				+ "                    \"content\": \"도전적인\"\n"
				+ "                },\n"
				+ "                {\n"
				+ "                    \"choice_id\": 3,\n"
				+ "                    \"order\": 3,\n"
				+ "                    \"content\": \"사교적인\"\n"
				+ "                }\n"
				+ "            ],\n"
				+ "            \"feedbacks\": [\n"
				+ "                {\n"
				+ "                    \"feedback_id\": \"1\",\n"
				+ "                    \"form_question_feedback_id\": \"2\",\n"
				+ "                    \"choice_id\": [\n"
				+ "                        2,\n"
				+ "                        1,\n"
				+ "                        3\n"
				+ "                    ],\n"
				+ "                    \"created_at\": \"2023-01-24T12:00:00\",\n"
				+ "                    \"is_read\": true,\n"
				+ "                    \"bookmark\": {\n"
				+ "                        \"bookmarked_at\": \"2023-01-24T12:00:00\",\n"
				+ "                        \"is_bookmarked\": true\n"
				+ "                    },\n"
				+ "                    \"reviewer\": {\n"
				+ "                        \"reviewer_id\": 1,\n"
				+ "                        \"nickname\": \"A\",\n"
				+ "                        \"collaboration_experience\": true,\n"
				+ "                        \"position\": \"developer\"\n"
				+ "                    }\n"
				+ "                }\n"
				+ "            ]\n"
				+ "        },\n"
				+ "        {\n"
				+ "            \"question_id\": 2,\n"
				+ "            \"order\": 2,\n"
				+ "            \"type\": \"short\",\n"
				+ "            \"form_type\": \"strength\",\n"
				+ "            \"title\": \"저는 UI, UI, GUI 중에 어떤 분야를 가장 잘하는 것 같나요?\",\n"
				+ "            \"feedbacks\": [\n"
				+ "                {\n"
				+ "                    \"feedback_id\": \"5\",\n"
				+ "                    \"form_question_feedback_id\": \"3\",\n"
				+ "                    \"created_at\": \"2023-01-24T12:00:00\",\n"
				+ "                    \"reply\": [\n"
				+ "                        \"예진이는 개발 관련 지식을 조금 더 공부해봐도\\n좋을 것 같아! 요즘 프로덕트 디자이너에겐 개발\\n지식을 아는 게 좋은 역량이 될 수 있어.\",\n"
				+ "                        \"안녕\"\n"
				+ "                    ],\n"
				+ "                    \"is_read\": false,\n"
				+ "                    \"bookmark\": { \n"
				+ "                        \"bookmarked_at\": \"2023-01-24T12:00:00\",\n"
				+ "                        \"is_bookmarked\": true\n"
				+ "                    },\n"
				+ "                    \"reviwer\": {\n"
				+ "                        \"reviewer_id\": 1,\n"
				+ "                        \"nickname\": \"A\",\n"
				+ "                        \"collaboration_experience\": true,\n"
				+ "                        \"position\": \"developer\"\n"
				+ "                    }\n"
				+ "                }\n"
				+ "            ]\n"
				+ "        }\n"
				+ "    ]\n"
				+ "}";
		}
		return "{\n"
			+ "    \"question_feedback\": [\n"
			+ "        {\n"
			+ "            \"question_id\": 1,\n"
			+ "            \"order\": 1,\n"
			+ "            \"type\": \"choice\",\n"
			+ "            \"form_type\": \"tendency\",\n"
			+ "            \"title\": \"성향\",\n"
			+ "            \"choices\": [\n"
			+ "                {\n"
			+ "                    \"choice_id\": 1,\n"
			+ "                    \"order\": 1,\n"
			+ "                    \"selected_count\": 5,\n"
			+ "                    \"content\": \"꼼꼼한\"\n"
			+ "                },\n"
			+ "                {\n"
			+ "                    \"choice_id\": 2,\n"
			+ "                    \"order\": 2,\n"
			+ "                    \"selected_count\": 10,\n"
			+ "                    \"content\": \"도전적인\"\n"
			+ "                },\n"
			+ "                {\n"
			+ "                    \"choice_id\": 3,\n"
			+ "                    \"order\": 3,\n"
			+ "                    \"selected_count\": 0,\n"
			+ "                    \"content\": \"사교적인\"\n"
			+ "                }\n"
			+ "            ]\n"
			+ "        }\n"
			+ "    ]\n"
			+ "}";
	}

	@GetMapping("/feedbacks/bookmarks")
	@ResponseStatus(HttpStatus.OK)
	Object findBookmarkedFeedbacks(@RequestParam("survey-id") Long surveyId) {
		return "{\n"
			+ "    \"question_feedback\": [\n"
			+ "        {\n"
			+ "            \"question_id\": 1,\n"
			+ "            \"order\": 1,\n"
			+ "            \"type\": \"choice\",\n"
			+ "            \"form_type\": \"tendency\",\n"
			+ "            \"title\": \"성향\",\n"
			+ "            \"choices\": [\n"
			+ "                {\n"
			+ "                    \"choice_id\": \"1\",\n"
			+ "                    \"order\": 1,\n"
			+ "                    \"content\": \"UI\"\n"
			+ "                },\n"
			+ "                {\n"
			+ "                    \"choice_id\": \"2\",\n"
			+ "                    \"order\": 2,\n"
			+ "                    \"content\": \"UX\"\n"
			+ "                },\n"
			+ "                {\n"
			+ "                    \"choice_id\": 3,\n"
			+ "                    \"order\": 3,\n"
			+ "                    \"content\": \"BX\"\n"
			+ "                }\n"
			+ "            ],\n"
			+ "            \"feedbacks\": [\n"
			+ "                {\n"
			+ "                    \"feedback_id\": \"1\",\n"
			+ "                    \"form_question_feedback_id\": \"2\",\n"
			+ "                    \"choice_id\": [\n"
			+ "                        2,\n"
			+ "                        1,\n"
			+ "                        3\n"
			+ "                    ],\n"
			+ "                    \"created_at\": \"2023-01-24T12:00:00\",\n"
			+ "                    \"is_read\": true,\n"
			+ "                    \"bookmark\": {\n"
			+ "                        \"bookmarked_at\": \"2023-01-24T12:00:00\",\n"
			+ "                        \"is_bookmarked\": true\n"
			+ "                    },\n"
			+ "                    \"reviewer\": {\n"
			+ "                        \"reviewer_id\": 1,\n"
			+ "                        \"nickname\": \"A\",\n"
			+ "                        \"collaboration_experience\": true,\n"
			+ "                        \"position\": \"developer\"\n"
			+ "                    }\n"
			+ "                }\n"
			+ "            ]\n"
			+ "        }\n"
			+ "    ]\n"
			+ "}";
	}

	@PatchMapping("/feedbacks/bookmarks")
	@ResponseStatus(HttpStatus.OK)
	void bookmark(@RequestParam("form-question-feedback-id") Long formQuestionFeedbackId) {
		// 200 OK 반환용 mock api 메소드로 비어있음
	}

}
