package me.nalab.luffy.api.acceptance.test.survey;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import me.nalab.luffy.api.acceptance.test.DatabaseCleaner;

public abstract class AbstractSurveyTestSupporter {

	private MockMvc mockMvc;
	private DatabaseCleaner databaseCleaner;
	private static final String API_VERSION = "/v1";
	private static final Set<String> tableNameSet = Set.of("target", "survey", "form_question", "choice");

	protected ResultActions createSurvey(String token, String content) throws Exception {
		return mockMvc.perform(MockMvcRequestBuilders
			.post(API_VERSION + "/surveys")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, token)
			.content(content));
	}

	protected ResultActions getLoginedSurveyId(String token) throws Exception {
		return mockMvc.perform(MockMvcRequestBuilders
			.get(API_VERSION + "/surveys-id")
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

