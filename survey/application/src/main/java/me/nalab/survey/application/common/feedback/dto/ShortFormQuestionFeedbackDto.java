package me.nalab.survey.application.common.feedback.dto;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShortFormQuestionFeedbackDto extends FormQuestionFeedbackDtoable {

	private List<String> replyList;

}
