package me.nalab.luffy.api.acceptance.test.feedback.create.response;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkResponse {

	private boolean isBookmarked;
	private Instant bookmarkedAt;
}
