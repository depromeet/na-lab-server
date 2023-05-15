package me.nalab.survey.domain.target;

import java.util.List;
import java.util.Objects;

import lombok.Builder;
import lombok.Getter;
import me.nalab.core.meta.coverage.Generated;
import me.nalab.survey.domain.survey.Survey;

@Builder
@Getter
public class Target {

	private final Long id;
	private final List<Survey> surveyList;
	private final String nickname;

	@Override
	public String toString() {
		return "Target{" +
			"id=" + id +
			", surveyList=" + surveyList +
			", nickname='" + nickname + '\'' +
			'}';
	}

	@Override
	@Generated
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof Target))
			return false;
		Target target = (Target)o;
		return id.equals(target.id) && surveyList.equals(target.surveyList) && nickname.equals(target.nickname);
	}

	@Override
	@Generated
	public int hashCode() {
		return Objects.hash(id, surveyList, nickname);
	}

}
