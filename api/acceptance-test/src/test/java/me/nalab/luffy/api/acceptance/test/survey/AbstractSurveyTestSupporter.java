package me.nalab.luffy.api.acceptance.test.survey;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import me.nalab.core.data.target.TargetEntity;
import me.nalab.core.idgenerator.idcore.IdGenerator;
import me.nalab.luffy.api.acceptance.test.AbstractDatabaseCleaner;

public abstract class AbstractSurveyTestSupporter extends AbstractDatabaseCleaner {

	private MockMvc mockMvc;
	private IdGenerator idGenerator;
	private static final String API_VERSION = "/v1";

	protected ResultActions createSurvey(String token, String content) throws Exception {
		return mockMvc.perform(MockMvcRequestBuilders
			.post(API_VERSION + "/surveys")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, token)
			.content(content));
	}

	@Transactional
	protected Long saveTarget(String name, LocalDateTime date) {
		TargetEntity targetEntity = TargetEntity.builder()
			.id(idGenerator.generate())
			.nickname(name)
			.createdAt(date)
			.updatedAt(date)
			.build();
		entityManager.persist(targetEntity);
		return targetEntity.getId();
	}

	@Autowired
	final void setMockMvc(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

	@Autowired
	final void setIdGenerator(IdGenerator idGenerator) {
		this.idGenerator = idGenerator;
	}

	@Override
	protected Set<String> getTableNameCollection() {
		return Set.of("target", "survey", "form_question", "choice");
	}

}

