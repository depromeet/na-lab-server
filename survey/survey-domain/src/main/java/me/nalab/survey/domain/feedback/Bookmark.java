package me.nalab.survey.domain.feedback;

import java.time.Instant;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class Bookmark {

	private final boolean isBookmarked;
	private final Instant bookmarkedAt;

}
