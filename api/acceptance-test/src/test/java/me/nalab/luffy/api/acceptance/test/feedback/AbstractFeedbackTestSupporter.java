package me.nalab.luffy.api.acceptance.test.feedback;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import me.nalab.luffy.api.acceptance.test.DatabaseCleaner;

public abstract class AbstractFeedbackTestSupporter {

	private MockMvc mockMvc;
	private DatabaseCleaner databaseCleaner;
	private static final String API_VERSION = "/v1";
	private static final Set<String> tableNameSet = Set.of("feedback", "form_feedback", "reviewer");

	//: TODO 여기에 Feedback api관련된 호출 메소드를 만들어 주세요. AbstractSurveyTestSupporter 참고해서 작성하면 됩니다.

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
