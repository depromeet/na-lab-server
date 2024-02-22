package me.nalab.gallery.app

import me.nalab.gallery.domain.response.GalleryPreviewDto
import me.nalab.survey.application.common.feedback.dto.BookmarkDto
import me.nalab.survey.application.common.feedback.dto.ChoiceFormQuestionFeedbackDto
import me.nalab.survey.application.common.feedback.dto.FeedbackDto
import me.nalab.survey.application.common.feedback.dto.ShortFormQuestionFeedbackDto
import me.nalab.survey.application.common.survey.dto.ChoiceFormQuestionDto
import me.nalab.survey.application.common.survey.dto.ChoiceFormQuestionDtoType
import me.nalab.survey.application.common.survey.dto.SurveyDto
import me.nalab.survey.application.common.survey.dto.TargetDto

fun toGalleryPreviewDto(
    target: TargetDto,
    survey: SurveyDto,
    feedbacks: List<FeedbackDto>,
): GalleryPreviewDto {
    return GalleryPreviewDto(
        target = GalleryPreviewDto.Target(
            targetId = target.id.toString(),
            nickname = target.nickname,
            position = target.position,
        ),
        survey = GalleryPreviewDto.Survey(
            surveyId = survey.id.toString(),
            feedbackCount = feedbacks.size,
            bookmarkedCount = 0,
            feedbacks = findLatestBookmark(feedbacks),
            tendencies = findTendencies(survey, feedbacks),
        )
    )
}

private fun findLatestBookmark(feedbacks: List<FeedbackDto>): List<String> {
    return feedbacks.filterBookmarkedFeedback()
        .mapBookmarkedWithReply()
        .sortedByDescending { (bookmark, _) -> bookmark.bookmarkedAt }
        .firstOrNull()
        ?.second ?: emptyList()
}

private fun List<FeedbackDto>.filterBookmarkedFeedback(): List<FeedbackDto> {
    return this.filter { feedback ->
        feedback.formQuestionFeedbackDtoableList.any { formQuestionFeedback ->
            formQuestionFeedback.bookmarkDto.isBookmarked
        }
    }
}

private fun List<FeedbackDto>.mapBookmarkedWithReply(): List<Pair<BookmarkDto, List<String>>> {
    return this.flatMap { bookmarkedFeedback ->
        bookmarkedFeedback.formQuestionFeedbackDtoableList
            .filterIsInstance<ShortFormQuestionFeedbackDto>()
            .map { shortQuestionFeedback -> shortQuestionFeedback.bookmarkDto to shortQuestionFeedback.replyList }
    }
}

private fun findTendencies(
    survey: SurveyDto,
    feedbacks: List<FeedbackDto>,
): List<GalleryPreviewDto.Survey.Tendency> {
    val tendencyQuestion = survey.formQuestionDtoableList
        .filterIsInstance<ChoiceFormQuestionDto>()
        .find { it.choiceFormQuestionDtoType == ChoiceFormQuestionDtoType.TENDENCY }
        ?: error("필수 유형 Tendency 를 찾을 수 없습니다.")

    val countPerTendency = mutableMapOf<String, Int>()

    feedbacks.mapTendencyFeedbacks(tendencyQuestion)
        .forEach { tendencyFeedback ->
            tendencyQuestion.choiceDtoList
                .filter { choice ->
                    tendencyFeedback.selectedChoiceIdSet.contains(choice.id)
                }
                .map { it.content }
                .forEach { tendencyContent ->
                    countPerTendency[tendencyContent] =
                        countPerTendency.getOrDefault(tendencyContent, 0) + 1
                }
        }

    return countPerTendency.map { (name, count) ->
        GalleryPreviewDto.Survey.Tendency(name, count)
    }
}

private fun List<FeedbackDto>.mapTendencyFeedbacks(
    tendencyQuestion: ChoiceFormQuestionDto,
): List<ChoiceFormQuestionFeedbackDto> {
    return this.flatMap {
        it.formQuestionFeedbackDtoableList
            .filterIsInstance<ChoiceFormQuestionFeedbackDto>()
            .filter { choiceQuestionFeedback ->
                choiceQuestionFeedback.questionId == tendencyQuestion.id
            }
    }
}
