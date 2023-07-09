package me.nalab.survey.application.service.findfeedback;

import me.nalab.survey.application.RandomSurveyDtoFixture;
import me.nalab.survey.application.TestIdGenerator;
import me.nalab.survey.application.common.feedback.dto.BookmarkDto;
import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.feedback.dto.ShortFormQuestionFeedbackDto;
import me.nalab.survey.application.common.feedback.mapper.FeedbackDtoMapper;
import me.nalab.survey.application.common.survey.mapper.SurveyDtoMapper;
import me.nalab.survey.application.port.out.persistence.findfeedback.FeedbackFindPort;
import me.nalab.survey.domain.feedback.Feedback;
import me.nalab.survey.domain.survey.FormQuestionable;
import me.nalab.survey.domain.survey.QuestionType;
import me.nalab.survey.domain.survey.Survey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static me.nalab.survey.application.RandomFeedbackDtoFixture.getRandomFeedbackDtoBySurvey;
import static me.nalab.survey.application.RandomFeedbackDtoFixture.getRandomReviewerDto;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BookmarkedFeedbackFindService.class, TestIdGenerator.class})
class BookmarkedFeedbackFindServiceTest {

    @Autowired
    private BookmarkedFeedbackFindService bookmarkedFeedbackFindService;

    @MockBean
    private FeedbackFindPort feedbackFindPort;

    @ParameterizedTest
    @MethodSource("feedbackFindSources")
    @DisplayName("Feedback 조회 성공 테스트 - Bookmark 된 응답 없음")
    void FIND_EMPTY_FEEDBACK(Survey survey, List<FeedbackDto> feedbackDtoList) {
        // given
        List<Feedback> feedbackList = feedbackDtoList.stream()
                                                     .map(f -> FeedbackDtoMapper.toDomain(survey, f))
                                                     .collect(Collectors.toList());
        when(feedbackFindPort.findAllFeedbackBySurveyId(anyLong())).thenReturn(feedbackList);

        // when

        List<FeedbackDto> result = bookmarkedFeedbackFindService.findAllBySurveyId(survey.getId());

        // then
        org.assertj.core.api.Assertions.assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Feedback 조회 성공 테스트 - Bookmark 된 응답 없음")
    void FIND_FEEDBACK() {
        // given
        Survey survey = SurveyDtoMapper.toSurvey(RandomSurveyDtoFixture.createRandomSurveyDto());
        List<Feedback> feedbackList = getBookmarkedFeedbackList(survey);
        when(feedbackFindPort.findAllFeedbackBySurveyId(anyLong())).thenReturn(feedbackList);

        // when
        List<FeedbackDto> result = bookmarkedFeedbackFindService.findAllBySurveyId(survey.getId());

        // then
        org.assertj.core.api.Assertions.assertThat(result).isNotEmpty();
        org.assertj.core.api.Assertions.assertThat(result)
                                       .allMatch(feedbackDto -> feedbackDto.getFormQuestionFeedbackDtoableList()
                                                                           .stream()
                                                                           .allMatch(formQuestionFeedbackDtoable -> formQuestionFeedbackDtoable.getBookmarkDto().isBookmarked()));
    }

    private static List<Feedback> getBookmarkedFeedbackList(Survey survey) {
        return Stream.of(getRandomFeedbackDtoBySurvey(survey),
                         getRandomFeedbackDtoBySurvey(survey),
                         getBookmarkedFeedbackDtoBySurvey(survey))
                     .map(feedbackDto -> FeedbackDtoMapper.toDomain(survey, feedbackDto))
                     .collect(Collectors.toList());
    }

    private static FeedbackDto getBookmarkedFeedbackDtoBySurvey(Survey survey) {
        FormQuestionable targetQuestion = survey.getFormQuestionableList()
                                                .stream()
                                                .filter(formQuestionable -> formQuestionable.getQuestionType() == QuestionType.SHORT)
                                                .findFirst()
                                                .orElseThrow();
        ShortFormQuestionFeedbackDto bookmarkedForm = ShortFormQuestionFeedbackDto.builder()
                                                                                  .questionId(targetQuestion.getId())
                                                                                  .bookmarkDto(
                                                                                          BookmarkDto.builder()
                                                                                                     .isBookmarked(true)
                                                                                                     .bookmarkedAt(Instant.now())
                                                                                                     .build())
                                                                                  .isRead(true)
                                                                                  .replyList(List.of("북마크된 텍스트"))
                                                                                  .build();

        return FeedbackDto.builder()
                   .surveyId(survey.getId())
                   .isRead(false)
                   .reviewerDto(getRandomReviewerDto())
                   .formQuestionFeedbackDtoableList(List.of(bookmarkedForm))
                   .createdAt(Instant.now())
                   .updatedAt(Instant.now())
                   .build();
    }

    private static Stream<Arguments> feedbackFindSources() {
        Survey survey = SurveyDtoMapper.toSurvey(RandomSurveyDtoFixture.createRandomSurveyDto());
        return Stream.of(
                Arguments.of(survey,
                             Arrays.asList(getRandomFeedbackDtoBySurvey(survey), getRandomFeedbackDtoBySurvey(survey),
                                           getRandomFeedbackDtoBySurvey(survey))), // feedback이 여러개
                Arguments.of(survey, Collections.singletonList(getRandomFeedbackDtoBySurvey(survey))), // feedback이 하나
                Arguments.of(survey, List.of()) // feedback이 없을때
                        );
    }

}
