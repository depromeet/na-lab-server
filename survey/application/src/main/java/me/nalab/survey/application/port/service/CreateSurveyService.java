package me.nalab.survey.application.port.service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import me.nalab.core.idgenerator.idcore.IdGenerator;
import me.nalab.survey.application.port.in.create.CreateSurveyUseCase;
import me.nalab.survey.application.port.in.create.SurveyCreateRequest;
import me.nalab.survey.application.port.out.persistence.CreateSurveyPort;
import org.springframework.transaction.annotation.Transactional;

import me.nalab.survey.application.port.out.persistence.FindSurveyPort;
import me.nalab.survey.domain.survey.Survey;

@Service
public class CreateSurveyService implements CreateSurveyUseCase {

	private final IdGenerator idGenerator;
	private final CreateSurveyPort createSurveyPort;
	private final FindSurveyPort findSurveyPort;

	public CreateSurveyService(IdGenerator idGenerator,
								CreateSurveyPort createSurveyPort,
								FindSurveyPort findSurveyPort) {
		this.idGenerator = idGenerator;
		this.createSurveyPort = createSurveyPort;
		this.findSurveyPort = findSurveyPort;
	}

	@Override
	@Transactional
	public void createSurvey(SurveyCreateRequest request) {
		Survey survey = Survey.builder()
			.id(idGenerator.generate())
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.formQuestionableList(
				request.getQuestionableList().stream()
					.map(qa -> qa.getConcreteQuestion(idGenerator))
					.collect(Collectors.toList())
			)
			.build();
		createSurveyPort.createSurvey(survey);
	}

	@Override
	@Transactional(readOnly = true)
	public Long findLatestSurveyId(Long targetId) {
		return findSurveyPort.findLatestSurveyId(targetId);
	}
}
