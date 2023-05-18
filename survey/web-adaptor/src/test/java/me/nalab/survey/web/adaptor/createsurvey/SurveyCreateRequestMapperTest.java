package me.nalab.survey.web.adaptor.createsurvey;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.nalab.survey.application.common.dto.ChoiceFormQuestionDto;
import me.nalab.survey.application.common.dto.ShortFormQuestionDto;
import me.nalab.survey.application.common.dto.SurveyDto;
import me.nalab.survey.web.adaptor.createsurvey.request.SurveyCreateRequest;

class SurveyCreateRequestMapperTest {

	@Test
	@DisplayName("SurveyCreate Request -> SurveyDto 성공 테스트")
	void SURVEY_CREATE_REQUEST_TO_SURVEY_DTO_SUCCESS() throws IOException, URISyntaxException {
		// given
		String json = read("./src/test/java/me/nalab/survey/web/adaptor/createsurvey/test.json");
		ObjectMapper objectMapper = new ObjectMapper();
		SurveyCreateRequest surveyCreateRequest = objectMapper.readValue(json, SurveyCreateRequest.class);

		// when
		SurveyDto surveyDto = SurveyCreateRequestMapper.toSurveyDto(surveyCreateRequest);

		// then
		assertRequestEqualDto(surveyCreateRequest, surveyDto);
	}

	private String read(String filePath) {
		Path path = Paths.get(filePath);
		StringBuilder sb = new StringBuilder();
		try {
			Files.readAllLines(path).forEach(sb::append);
		} catch(IOException ioe) {
			throw new IllegalStateException("Cannot read file from path \"" + filePath + "\"");
		}
		return sb.toString();
	}

	private void assertRequestEqualDto(SurveyCreateRequest surveyCreateRequest, SurveyDto surveyDto) {
		assertEquals(surveyCreateRequest.getFormQuestionRequestableList().size(),
			surveyDto.getFormQuestionDtoableList().size());
		surveyDto.getFormQuestionDtoableList().forEach(
			fqd -> {
				assertNotNull(fqd.getTitle());
				assertNotNull(fqd.getCreatedAt());
				assertEquals(fqd.getCreatedAt(), fqd.getUpdatedAt());
				assertNotNull(fqd.getQuestionDtoType());
				assertNotNull(fqd.getOrder());
				if(fqd instanceof ChoiceFormQuestionDto) {
					ChoiceFormQuestionDto choiceFormQuestionDto = (ChoiceFormQuestionDto)fqd;
					assertTrue(choiceFormQuestionDto.getChoiceDtoList().size() >= 1);
					assertNotNull(choiceFormQuestionDto.getChoiceFormQuestionDtoType());
					choiceFormQuestionDto.getChoiceDtoList().forEach(
						cd -> {
							assertTrue(cd.getOrder() >= 1);
							assertNotNull(cd.getContent());
						}
					);
				}
				if(fqd instanceof ShortFormQuestionDto) {
					ShortFormQuestionDto shortFormQuestionDto = (ShortFormQuestionDto)fqd;
					assertNotNull(shortFormQuestionDto.getShortFormQuestionDtoType());
				}
			}
		);
	}

}
