package me.nalab.auth.mock.interceptor;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class MockAuthInterceptorTestController {

	@GetMapping(path = {
		"/v1/users",
		"/v1/surveys-id",
		"/v1/questions",
		"/v1/feedbacks",
		"/v1/feedbacks/{feedback-id}",
		"/v1/feedbacks/summary",
		"/v1/feedbacks?question-id",
	})
	Long returnLoginId(@RequestAttribute("logined") Long loginId,
		@RequestParam(name = "survey-id", required = false) Long surveyId,
		@RequestParam(name = "question-id", required = false) Long questionId,
		@PathVariable(name = "feedback-id", required = false) Long feedbackId) {
		return loginId;
	}

	@PostMapping(path = {
		"/v1/surveys",
	})
	Long returnLoginId(@RequestAttribute("logined") Long loginId,
		@RequestBody Map<String, Object> someBody) {
		return loginId;
	}
}
