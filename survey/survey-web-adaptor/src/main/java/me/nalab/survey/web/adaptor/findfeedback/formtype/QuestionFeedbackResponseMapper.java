package me.nalab.survey.web.adaptor.findfeedback.formtype;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import me.nalab.survey.application.common.feedback.dto.ChoiceFormQuestionFeedbackDto;
import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.feedback.dto.ShortFormQuestionFeedbackDto;
import me.nalab.survey.application.common.survey.dto.ChoiceDto;
import me.nalab.survey.application.common.survey.dto.ChoiceFormQuestionDto;
import me.nalab.survey.application.common.survey.dto.FormQuestionDtoable;
import me.nalab.survey.application.common.survey.dto.QuestionDtoType;
import me.nalab.survey.application.common.survey.dto.ShortFormQuestionDto;
import me.nalab.survey.web.adaptor.findfeedback.formtype.response.QuestionFeedbackResponse;
import me.nalab.survey.web.adaptor.findfeedback.formtype.response.formquestion.ChoiceFormQuestionResponse;
import me.nalab.survey.web.adaptor.findfeedback.formtype.response.formquestion.ChoiceResponse;
import me.nalab.survey.web.adaptor.findfeedback.formtype.response.formquestion.ShortFormQuestionResponse;
import me.nalab.survey.web.adaptor.findfeedback.formtype.response.formquestion.ShortResponse;

final class QuestionFeedbackResponseMapper {

	private QuestionFeedbackResponseMapper() {
		throw new UnsupportedOperationException("Cannot invoke constructor \"QuestionFeedbackResponseMapper()\"");
	}

	static QuestionFeedbackResponse toFeedbackQuestionResponse(List<FormQuestionDtoable> formQuestionDtoableList,
		List<FeedbackDto> feedbackDtoList) {
		return QuestionFeedbackResponse.builder()
			.abstractFormQuestionResponseList(formQuestionDtoableList.stream()
				.map(q -> {
						if (q.getQuestionDtoType() == QuestionDtoType.CHOICE) {
							return toChoiceFormQuestionResponse((ChoiceFormQuestionDto)q, feedbackDtoList);
						}
						return toShortFormQuestionResponse((ShortFormQuestionDto)q, feedbackDtoList);
					}
				).collect(Collectors.toList())).build();
	}

	private static ChoiceFormQuestionResponse toChoiceFormQuestionResponse(ChoiceFormQuestionDto choiceFormQuestionDto,
		List<FeedbackDto> feedbackDtoList) {
		ChoiceFormQuestionResponse choiceFormQuestionResponse = ChoiceFormQuestionResponse.builder()
			.questionId(String.valueOf(choiceFormQuestionDto.getId()))
			.order(choiceFormQuestionDto.getOrder())
			.type(choiceFormQuestionDto.getQuestionDtoType().name().toLowerCase())
			.title(choiceFormQuestionDto.getQuestionDtoType().name().toLowerCase())
			.formType(choiceFormQuestionDto.getChoiceFormQuestionDtoType().name().toLowerCase())
			.title(choiceFormQuestionDto.getTitle())
			.choiceResponseList(toChoiceResponseList(choiceFormQuestionDto.getChoiceDtoList()))
			.build();
		updateSelectedCount(choiceFormQuestionResponse, feedbackDtoList);
		return choiceFormQuestionResponse;
	}

	private static void updateSelectedCount(ChoiceFormQuestionResponse choiceFormQuestionResponse,
		List<FeedbackDto> feedbackDtoList) {
		Long questionId = Long.valueOf(choiceFormQuestionResponse.getQuestionId());

		feedbackDtoList
			.forEach(c -> c.getFormQuestionFeedbackDtoableList()
				.forEach(q -> {
					if (Objects.equals(q.getQuestionId(), questionId)) {
						Set<Long> selectedChoiceIdSet = ((ChoiceFormQuestionFeedbackDto)q).getSelectedChoiceIdSet();
						selectedChoiceIdSet
							.forEach(sc -> {
								choiceFormQuestionResponse.getChoiceResponseList()
									.forEach(cq -> {
										if (Objects.equals(sc, Long.valueOf(cq.getChoiceId())))
											cq.updateSelectedCount();
									});
							});
					}
				})
			);
	}

	private static List<ChoiceResponse> toChoiceResponseList(List<ChoiceDto> choiceDtoList) {
		return choiceDtoList.stream()
			.map(c -> ChoiceResponse.builder()
				.choiceId(String.valueOf(c.getId()))
				.order(c.getOrder())
				.selectedCount(0)
				.content(c.getContent())
				.build())
			.collect(Collectors.toList());
	}

	private static ShortFormQuestionResponse toShortFormQuestionResponse(ShortFormQuestionDto shortFormQuestionDto,
		List<FeedbackDto> feedbackDtoList) {
		List<ShortResponse> shortResponseList = new ArrayList<>();
		ShortFormQuestionResponse shortFormQuestionResponse = ShortFormQuestionResponse.builder()
			.questionId(String.valueOf(shortFormQuestionDto.getId()))
			.order(shortFormQuestionDto.getOrder())
			.type(shortFormQuestionDto.getQuestionDtoType().name().toLowerCase())
			.title(shortFormQuestionDto.getQuestionDtoType().name().toLowerCase())
			.formType(shortFormQuestionDto.getShortFormQuestionDtoType().name().toLowerCase())
			.title(shortFormQuestionDto.getTitle())
			.shortResponseList(shortResponseList)
			.build();
		toShortResponseList(shortFormQuestionResponse, feedbackDtoList);
		return shortFormQuestionResponse;
	}

	private static void toShortResponseList(ShortFormQuestionResponse shortFormQuestionResponse,
		List<FeedbackDto> feedbackDtoList) {
		feedbackDtoList
			.forEach(f -> {
				f.getFormQuestionFeedbackDtoableList().stream()
					.filter(q -> Objects.equals(q.getQuestionId(),
						Long.valueOf(shortFormQuestionResponse.getQuestionId())))
					.forEach(sq -> shortFormQuestionResponse.getShortResponseList().add(
						ShortResponse.builder()
							.feedbackId(String.valueOf(f.getId()))
							.createdAt(
								ZonedDateTime.ofInstant(f.getCreatedAt(), ZoneId.of("Asia/Seoul")).toLocalDateTime())
							.replyList(((ShortFormQuestionFeedbackDto)sq).getReplyList())
							.build()

					));
			});

	}

}
