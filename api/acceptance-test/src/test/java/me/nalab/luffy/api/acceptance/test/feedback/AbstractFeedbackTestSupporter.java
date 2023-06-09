package me.nalab.luffy.api.acceptance.test.feedback;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import me.nalab.luffy.api.acceptance.test.DatabaseCleaner;
import me.nalab.luffy.api.acceptance.test.survey.AbstractSurveyTestSupporter;

public abstract class AbstractFeedbackTestSupporter extends AbstractSurveyTestSupporter {

	private MockMvc mockMvc;
	private DatabaseCleaner databaseCleaner;
	private static final String API_VERSION = "/v1";
	private static final Set<String> TABLE_NAME_SET = Set.of("target", "survey", "form_question", "choice", "feedback",
		"form_feedback", "reviewer");

	protected ResultActions createFeedback(Long surveyId, String content) throws Exception {
		return mockMvc.perform(MockMvcRequestBuilders
			.post("/v1/feedbacks?survey-id=" + surveyId)
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.content(content)
		);
	}

	protected ResultActions summarizeReviewer(String token, Long surveyId) throws Exception {
		return mockMvc.perform(MockMvcRequestBuilders
			.get("/v1/reviewers/summary?survey-id=" + surveyId)
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, token)
		);
	}

	protected ResultActions findFeedbackSummary(String token, String surveyId) throws Exception {
		return mockMvc.perform(MockMvcRequestBuilders
			.get(API_VERSION + "/feedbacks/summary")
			.queryParam("survey-id", surveyId)
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, token)
		);
	}

	protected ResultActions findReviewers(String token, String surveyId) throws Exception {
		return mockMvc.perform(MockMvcRequestBuilders
			.get(API_VERSION + "/reviewers")
			.queryParam("survey-id", surveyId)
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, token)
		);
	}

	protected ResultActions findFeedback(String token, Long surveyId) throws Exception {
		return mockMvc.perform(MockMvcRequestBuilders
									   .get(String.format("/v2/surveys/%d/feedbacks", surveyId))
									   .accept(MediaType.APPLICATION_JSON)
									   .contentType(MediaType.APPLICATION_JSON)
									   .header(HttpHeaders.AUTHORIZATION, token)
							  );
	}

	protected ResultActions findBookmarkedFeedback(Long surveyId) throws Exception {
		return mockMvc.perform(MockMvcRequestBuilders
			.get("/v1/feedbacks/bookmarks")
			.queryParam("survey-id", surveyId.toString())
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
		);
	}

	protected ResultActions findSpecific(String token, Long feedbackId) throws Exception {
		return mockMvc.perform(MockMvcRequestBuilders
			.get("/v1/feedbacks/" + feedbackId)
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, token)
		);
	}

	protected ResultActions replaceBookmark(String token, String form_question_feedback_id) throws Exception {
		return mockMvc.perform(MockMvcRequestBuilders
			.patch("/v1/feedbacks/bookmarks")
			.queryParam("form-question-feedback-id", form_question_feedback_id)
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, token)
		);
	}

	protected ResultActions findFeedbackByType(Long surveyId, String formType) throws Exception {
		return mockMvc.perform(MockMvcRequestBuilders
			.get("/v2/feedbacks?survey-id=" + surveyId)
			.queryParam("form-type", formType)
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
		);
	}

	@Autowired
	final void setMockMvc(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

	@Autowired
	public void setDatabaseCleaner(DatabaseCleaner databaseCleaner) {
		this.databaseCleaner = databaseCleaner;
	}

	@BeforeEach
	void cleanDb() {
		databaseCleaner.dbCleanUp(TABLE_NAME_SET);
	}

}
