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

public abstract class AbstractFeedbackTestSupporter extends AbstractSurveyTestSupporter  {

	private MockMvc mockMvc;
	private DatabaseCleaner databaseCleaner;
	private static final String API_VERSION = "/v1";
	private static final Set<String> tableNameSet = Set.of("feedback", "form_feedback", "reviewer");

	//: TODO 여기에 Feedback api관련된 호출 메소드를 만들어 주세요. AbstractSurveyTestSupporter 참고해서 작성하면 됩니다.

	//: TODO
	protected ResultActions findFeedbackSummary(String token, String surveyId) throws Exception {
		return mockMvc.perform(MockMvcRequestBuilders
			.get(API_VERSION + "/feedbacks/summary")
			.queryParam("survey_id", surveyId)
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, token)
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
		databaseCleaner.dbCleanUp(tableNameSet);
	}

}
