package me.nalab.survey.application.service.summaryreviewer;

import static me.nalab.survey.application.port.in.web.summaryreviewer.ReviewerSummarizeUseCase.ReviewerSummaryDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.nalab.survey.application.port.in.web.summaryreviewer.ReviewerSummarizeUseCase;
import me.nalab.survey.application.port.out.persistence.summaryreviewer.ReviewerFindPort;
import me.nalab.survey.application.port.out.persistence.summaryreviewer.SurveyExistCheckPort;
import me.nalab.survey.domain.feedback.Reviewer;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ReviewerSummaryGetService.class})
class ReviewerSummaryGetServiceTest {

	@Autowired
	private ReviewerSummarizeUseCase reviewerSummaryGetUseCase;

	@MockBean
	private ReviewerFindPort reviewerFindPort;

	@MockBean
	private SurveyExistCheckPort surveyExistCheckPort;

	@ParameterizedTest
	@MethodSource("reviewerListSource")
	void GET_REVIEWER_SUMMARY_SUCCESS(ReviewerSummaryDto expected, List<Reviewer> reviewerList) {
		// when
		when(reviewerFindPort.findAllReviewer(anyLong())).thenReturn(reviewerList);
		when(surveyExistCheckPort.isExistSurveyBySurveyId(anyLong())).thenReturn(true);

		ReviewerSummaryDto summaryResponse = reviewerSummaryGetUseCase.summarizeReviewerBySurveyId(anyLong());

		// then
		assertEquals(expected, summaryResponse);
	}

	private static Stream<Arguments> reviewerListSource() {
		return Stream.of(
			Arguments.of( // expected
				ReviewerSummaryDto.builder()
					.position(ReviewerSummaryDto.Position.builder()
						.positionReplyMap(Map.of("developer", 2, "designer", 1, "product-manager", 1))
						.build())
					.collaborationExperience(ReviewerSummaryDto.CollaborationExperience.builder()
						.yes(3)
						.no(1)
						.build()
					).build(),
				List.of( // response
					getReviewer("developer", true),
					getReviewer("product-manager", false),
					getReviewer("designer", true),
					getReviewer("developer", true)
				)
			),

			Arguments.of(
				ReviewerSummaryDto.builder()
					.position(ReviewerSummaryDto.Position.builder()
						.positionReplyMap(Map.of())
						.build())
					.collaborationExperience(ReviewerSummaryDto.CollaborationExperience
						.builder()
						.build()
					).build(),

				List.of()
			),

			Arguments.of(
				ReviewerSummaryDto.builder()
					.position(ReviewerSummaryDto.Position.builder()
						.positionReplyMap(Map.of("developer", 1))
						.build())
					.collaborationExperience(ReviewerSummaryDto.CollaborationExperience.builder()
						.yes(1)
						.build()
					).build(),

				List.of(getReviewer("developer", true))
			)
		);
	}

	private static Reviewer getReviewer(String position, boolean collaborationExperience) {
		return Reviewer.builder()
			.position(position)
			.collaborationExperience(collaborationExperience)
			.build();
	}

}
