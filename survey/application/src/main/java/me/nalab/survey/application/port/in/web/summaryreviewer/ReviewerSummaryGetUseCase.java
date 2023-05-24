package me.nalab.survey.application.port.in.web.summaryreviewer;

import java.util.Map;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 질문폼에 리뷰한 리뷰어들의 정보를 통합, 요약해서 반환합니다.
 */
public interface ReviewerSummaryGetUseCase {

	/**
	 * surveyId를 인자로 받고, surveyId에 리뷰한 리뷰어의 정보를 요약해서 ReviewerSummaryDto 로 반환합니다.
	 *
	 * @param surveyId 조회할 survey의 id
	 * @return ReviewerSummaryDto 요약된 리뷰어의 정보
	 */
	ReviewerSummaryDto getReviewerSummary(Long surveyId);

	@Getter
	@Builder
	@ToString
	@EqualsAndHashCode
	class ReviewerSummaryDto {

		private final CollaborationExperience collaborationExperience;
		private final Position position;

		@Getter
		@Builder
		@ToString
		@EqualsAndHashCode
		public static class CollaborationExperience {
			private final int yes;
			private final int no;
		}

		@Getter
		@Builder
		@ToString
		@EqualsAndHashCode
		public static class Position {
			private final Map<String, Integer> positionReplyMap;
		}

	}

}
