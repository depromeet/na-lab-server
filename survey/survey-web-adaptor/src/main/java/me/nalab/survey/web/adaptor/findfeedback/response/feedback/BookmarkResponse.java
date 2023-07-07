package me.nalab.survey.web.adaptor.findfeedback.response.feedback;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class BookmarkResponse {

	@JsonProperty("is_bookmarked")
	private final boolean isBookmarked;

	@JsonProperty("bookmarked_at")
	private final LocalDateTime bookmarkedAt;

}
