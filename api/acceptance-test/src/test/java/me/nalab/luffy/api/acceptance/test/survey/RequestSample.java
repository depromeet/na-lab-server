package me.nalab.luffy.api.acceptance.test.survey;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import me.nalab.luffy.api.acceptance.test.survey.dto.ChoiceFormQuestionRequest;
import me.nalab.luffy.api.acceptance.test.survey.dto.ChoiceRequest;
import me.nalab.luffy.api.acceptance.test.survey.dto.ShortFormQuestionRequest;
import me.nalab.luffy.api.acceptance.test.survey.dto.SurveyCreateRequest;

public final class RequestSample {

	private static final ObjectMapper OBJECT_MAPPER;
	public static final String DEFAULT_JSON;
	public static final String CUSTOM_JSON;
	public static final String FAILED_JSON;

	static {
		OBJECT_MAPPER = new ObjectMapper();
		OBJECT_MAPPER.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

		DEFAULT_JSON = loadDefaultJson();
		CUSTOM_JSON = loadCustomJson();
		FAILED_JSON = failedJson();
	}

	private static String loadDefaultJson() {
		try {
			return OBJECT_MAPPER.writeValueAsString(SurveyCreateRequest.builder()
				.questionCount(4)
				.formQuestionRequestableList(
					List.of(ChoiceFormQuestionRequest.builder()
							.questionType("choice")
							.choiceFormQuestionType("custom")
							.title("당신의 포지션을 알려주세요.")
							.maxSelectableCount(2)
							.order(3)
							.choiceRequestList(
								List.of(
									ChoiceRequest.builder()
										.content("developer")
										.order(1)
										.build(),
									ChoiceRequest.builder()
										.content("designer")
										.order(2)
										.build(),
									ChoiceRequest.builder()
										.content("pm")
										.order(3)
										.build(),
									ChoiceRequest.builder()
										.content("others")
										.order(4)
										.build()
								)
							)
							.build(),
						ChoiceFormQuestionRequest.builder()
							.questionType("choice")
							.choiceFormQuestionType("tendency")
							.title("예진님의 성향은 어떤 것이 돋보이나요?")
							.maxSelectableCount(5)
							.order(4)
							.choiceRequestList(
								List.of(
									ChoiceRequest.builder()
										.content("적응력 좋은")
										.order(1)
										.build(),
									ChoiceRequest.builder()
										.content("완벽주의적인")
										.order(2)
										.build(),
									ChoiceRequest.builder()
										.content("사교적인")
										.order(3)
										.build(),
									ChoiceRequest.builder()
										.content("눈치빠른")
										.order(4)
										.build(),
									ChoiceRequest.builder()
										.content("긍정적인")
										.order(5)
										.build(),
									ChoiceRequest.builder()
										.content("현실적인")
										.order(6)
										.build(),
									ChoiceRequest.builder()
										.content("통찰력 있는")
										.order(7)
										.build(),
									ChoiceRequest.builder()
										.content("추진력 있는")
										.order(8)
										.build()
								)
							)
							.build(),
						ShortFormQuestionRequest.builder()
							.questionType("short")
							.shortFormQuestionType("strength")
							.title("당신이 생각하는 예진님의 직무적 강점은 무엇인가요?")
							.order(5)
							.build(),
						ShortFormQuestionRequest.builder()
							.questionType("short")
							.shortFormQuestionType("custom")
							.title("당신이 생각하는 예진님의 직무적 약점은 무엇인가요?")
							.order(6)
							.build()
					)
				)
				.build()
			);
		} catch(JsonProcessingException jpe) {
			throw new IllegalStateException(
				"Cannot start acceptance test fail to load \"DEFAULT_JSON\" " + jpe.getMessage());
		}
	}

	private static String loadCustomJson() {
		try {
			return OBJECT_MAPPER.writeValueAsString(SurveyCreateRequest.builder()
				.questionCount(8)
				.formQuestionRequestableList(
					List.of(ChoiceFormQuestionRequest.builder()
							.questionType("choice")
							.choiceFormQuestionType("custom")
							.title("당신의 포지션을 알려주세요.")
							.maxSelectableCount(2)
							.order(3)
							.choiceRequestList(
								List.of(
									ChoiceRequest.builder()
										.content("developer")
										.order(1)
										.build(),
									ChoiceRequest.builder()
										.content("designer")
										.order(2)
										.build(),
									ChoiceRequest.builder()
										.content("pm")
										.order(3)
										.build(),
									ChoiceRequest.builder()
										.content("others")
										.order(4)
										.build()
								)
							)
							.build(),
						ChoiceFormQuestionRequest.builder()
							.questionType("choice")
							.choiceFormQuestionType("tendency")
							.title("예진님의 성향은 어떤 것이 돋보이나요?")
							.maxSelectableCount(5)
							.order(4)
							.choiceRequestList(
								List.of(
									ChoiceRequest.builder()
										.content("적응력 좋은")
										.order(1)
										.build(),
									ChoiceRequest.builder()
										.content("완벽주의적인")
										.order(2)
										.build(),
									ChoiceRequest.builder()
										.content("사교적인")
										.order(3)
										.build(),
									ChoiceRequest.builder()
										.content("눈치빠른")
										.order(4)
										.build(),
									ChoiceRequest.builder()
										.content("긍정적인")
										.order(5)
										.build(),
									ChoiceRequest.builder()
										.content("현실적인")
										.order(6)
										.build(),
									ChoiceRequest.builder()
										.content("통찰력 있는")
										.order(7)
										.build(),
									ChoiceRequest.builder()
										.content("추진력 있는")
										.order(8)
										.build()
								)
							)
							.build(),
						ShortFormQuestionRequest.builder()
							.questionType("short")
							.shortFormQuestionType("strength")
							.title("당신이 생각하는 예진님의 직무적 강점은 무엇인가요?")
							.order(5)
							.build(),
						ShortFormQuestionRequest.builder()
							.questionType("short")
							.shortFormQuestionType("custom")
							.title("당신이 생각하는 예진님의 직무적 약점은 무엇인가요?")
							.order(6)
							.build(),
						ChoiceFormQuestionRequest.builder()
							.questionType("choice")
							.choiceFormQuestionType("custom")
							.title("커스텀 객관식 1")
							.maxSelectableCount(2)
							.order(7)
							.choiceRequestList(
								List.of(
									ChoiceRequest.builder()
										.content("네, 있어요")
										.order(1)
										.build(),
									ChoiceRequest.builder()
										.content("없어요")
										.order(2)
										.build()
								)
							)
							.build(),
						ShortFormQuestionRequest.builder()
							.questionType("short")
							.shortFormQuestionType("custom")
							.title("커스텀 주관식 1")
							.order(8)
							.build(),
						ShortFormQuestionRequest.builder()
							.questionType("short")
							.shortFormQuestionType("custom")
							.title("커스텀 주관식 2")
							.order(9)
							.build(),
						ChoiceFormQuestionRequest.builder()
							.questionType("choice")
							.choiceFormQuestionType("custom")
							.title("커스텀 객관식 2")
							.maxSelectableCount(2)
							.order(10)
							.choiceRequestList(
								List.of(
									ChoiceRequest.builder()
										.content("네, 있어요")
										.order(1)
										.build(),
									ChoiceRequest.builder()
										.content("없어요")
										.order(2)
										.build(),
									ChoiceRequest.builder()
										.content("몰라요")
										.order(3)
										.build()
								)
							)
							.build()
					)
				)
				.build()
			);
		} catch(JsonProcessingException jpe) {
			throw new IllegalStateException("Cannot start acceptance test fail to load \"CUSTOM_JSON\"");
		}
	}

	private static String failedJson() {
		try {
			return OBJECT_MAPPER.writeValueAsString(SurveyCreateRequest.builder()
				.questionCount(1000)
				.formQuestionRequestableList(
					List.of(ChoiceFormQuestionRequest.builder()
							.questionType("choice")
							.choiceFormQuestionType("custom")
							.title("당신의 포지션을 알려주세요.")
							.maxSelectableCount(2)
							.order(3)
							.choiceRequestList(
								List.of(
									ChoiceRequest.builder()
										.content("developer")
										.order(1)
										.build(),
									ChoiceRequest.builder()
										.content("designer")
										.order(2)
										.build(),
									ChoiceRequest.builder()
										.content("pm")
										.order(3)
										.build(),
									ChoiceRequest.builder()
										.content("others")
										.order(4)
										.build()
								)
							)
							.build(),
						ChoiceFormQuestionRequest.builder()
							.questionType("choice")
							.choiceFormQuestionType("tendency")
							.title("예진님의 성향은 어떤 것이 돋보이나요?")
							.maxSelectableCount(5)
							.order(4)
							.choiceRequestList(
								List.of(
									ChoiceRequest.builder()
										.content("적응력 좋은")
										.order(1)
										.build(),
									ChoiceRequest.builder()
										.content("완벽주의적인")
										.order(2)
										.build(),
									ChoiceRequest.builder()
										.content("사교적인")
										.order(3)
										.build(),
									ChoiceRequest.builder()
										.content("눈치빠른")
										.order(4)
										.build(),
									ChoiceRequest.builder()
										.content("긍정적인")
										.order(5)
										.build(),
									ChoiceRequest.builder()
										.content("현실적인")
										.order(6)
										.build(),
									ChoiceRequest.builder()
										.content("통찰력 있는")
										.order(7)
										.build(),
									ChoiceRequest.builder()
										.content("추진력 있는")
										.order(8)
										.build()
								)
							)
							.build(),
						ShortFormQuestionRequest.builder()
							.questionType("short")
							.shortFormQuestionType("strength")
							.title("당신이 생각하는 예진님의 직무적 강점은 무엇인가요?")
							.order(5)
							.build(),
						ShortFormQuestionRequest.builder()
							.questionType("short")
							.shortFormQuestionType("custom")
							.title("당신이 생각하는 예진님의 직무적 약점은 무엇인가요?")
							.order(6)
							.build()
					)
				)
				.build()
			);
		} catch(JsonProcessingException jpe) {
			throw new IllegalStateException(
				"Cannot start acceptance test fail to load \"DEFAULT_JSON\" " + jpe.getMessage());
		}
	}

}
