package me.nalab.survey.jpa.adaptor.common.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import me.nalab.core.data.target.SurveyBookmarkEntity;
import me.nalab.core.data.target.TargetEntity;
import me.nalab.survey.domain.target.SurveyBookmark;
import me.nalab.survey.domain.target.Target;

public class TargetEntityMapper {

	private TargetEntityMapper() {
		throw new UnsupportedOperationException("Cannot invoke constructor \"TargetEntityMapper()\"");
	}

	public static TargetEntity toTargetEntity(Target target) {
		return TargetEntity.builder()
			.id(target.getId())
			.nickname(target.getNickname())
			.position(target.getPosition())
			.imageUrl(target.getImageUrl())
			.job(target.getJob())
			.bookmarkedSurveys(toSurveyBookmarkEntity(target.getBookmarkedSurveys()))
			.build();
	}

    public static Set<SurveyBookmarkEntity> toSurveyBookmarkEntity(Set<SurveyBookmark> surveyBookmarks) {
        return surveyBookmarks.stream()
            .map(surveyBookmark -> new SurveyBookmarkEntity(surveyBookmark.surveyId()))
            .collect(Collectors.toSet());
    }

	public static Target toTarget(TargetEntity targetEntity) {
		return Target.builder()
			.id(targetEntity.getId())
			.nickname(targetEntity.getNickname())
			.position(targetEntity.getPosition())
			.job(targetEntity.getJob())
            .bookmarkedSurveys(toSurveyBookmark(targetEntity.getBookmarkedSurveys()))
			.build();
	}

    public static Set<SurveyBookmark> toSurveyBookmark(Set<SurveyBookmarkEntity> surveyBookmarkEntities) {
        return surveyBookmarkEntities.stream()
            .map(surveyBookmarkEntity -> new SurveyBookmark(surveyBookmarkEntity.getSurveyId()))
            .collect(Collectors.toSet());
    }
}
