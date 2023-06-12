package me.nalab.survey.web.adaptor.survey.find.response;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class TargetResponse {

	private String id;

	private String nickname;

}
