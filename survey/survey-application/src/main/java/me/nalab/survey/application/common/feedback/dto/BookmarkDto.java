package me.nalab.survey.application.common.feedback.dto;

import java.time.Instant;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class BookmarkDto {

	private final boolean isBookmarked;
	private final Instant bookmarkedAt;

}
