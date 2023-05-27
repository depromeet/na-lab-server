package me.nalab.survey.web.adaptor.findspecific.response;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ShortFormFeedbackResponse extends FormFeedbackResponseable {

	private final List<String> reply;
}
