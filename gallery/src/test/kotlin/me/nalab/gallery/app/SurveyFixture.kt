package me.nalab.gallery.app

import me.nalab.core.time.TimeUtil
import me.nalab.survey.application.common.feedback.dto.*
import me.nalab.survey.application.common.survey.dto.*
import java.time.Instant

fun targetDto(
    id: Long = 0L,
    nickname: String = "이준영",
    position: String = "백엔드 엔지니어",
): TargetDto {
    return TargetDto.builder()
        .id(id)
        .nickname(nickname)
        .position(position)
        .build()
}

fun surveyDto(
    id: Long = 0L,
    targetId: Long = 0L,
    formQuestionDtos: List<FormQuestionDtoable> = listOf(
        choiceFormQuestionDto(),
        shortFormQuestionDto()
    ),
    time: Instant = TimeUtil.toInstant(),
): SurveyDto {
    return SurveyDto.builder()
        .id(id)
        .targetId(targetId)
        .formQuestionDtoableList(formQuestionDtos)
        .createdAt(time)
        .updatedAt(time)
        .build()
}

fun choiceFormQuestionDto(
    id: Long = 0L,
    time: Instant = TimeUtil.toInstant(),
    type: ChoiceFormQuestionDtoType = ChoiceFormQuestionDtoType.TENDENCY,
    choices: List<ChoiceDto> = listOf(choiceDto()),
    maxSelectableCount: Int = 5,
    order: Int = 1,
): ChoiceFormQuestionDto {
    return ChoiceFormQuestionDto.builder()
        .id(id)
        .createdAt(time)
        .updatedAt(time)
        .choiceFormQuestionDtoType(type)
        .choiceDtoList(choices)
        .maxSelectableCount(maxSelectableCount)
        .order(order)
        .build()
}

fun choiceDto(
    id: Long = 0L,
    content: String = "경청하는",
    order: Int = 1,
): ChoiceDto {
    return ChoiceDto.builder()
        .id(id)
        .content(content)
        .order(order)
        .build()
}

fun shortFormQuestionDto(
    id: Long = 0L,
    time: Instant = TimeUtil.toInstant(),
    type: ShortFormQuestionDtoType = ShortFormQuestionDtoType.CUSTOM,
    title: String = "제가 고쳐야할점을 알려주세요.",
    order: Int = 1,
): ShortFormQuestionDto {
    return ShortFormQuestionDto.builder()
        .id(id)
        .createdAt(time)
        .updatedAt(time)
        .shortFormQuestionDtoType(type)
        .order(order)
        .title(title)
        .build()
}

fun feedbackDto(
    id: Long = 0L,
    surveyId: Long = 0L,
    time: Instant = TimeUtil.toInstant(),
    formQuestionFeedbackDtos: List<FormQuestionFeedbackDtoable> = listOf(
        choiceFormQuestionFeedbackDto(),
        shortFormQuestionFeedbackDto()
    ),
    isRead: Boolean = true,
): FeedbackDto {
    return FeedbackDto.builder()
        .id(id)
        .surveyId(surveyId)
        .createdAt(time)
        .updatedAt(time)
        .formQuestionFeedbackDtoableList(formQuestionFeedbackDtos)
        .isRead(isRead)
        .build()
}

fun choiceFormQuestionFeedbackDto(
    id: Long = 0L,
    questionId: Long = 0L,
    isRead: Boolean = false,
    bookmarkDto: BookmarkDto = bookmarkDto(),
    selectedChoiceIdSet: Set<Long> = setOf(0L),
): ChoiceFormQuestionFeedbackDto {
    return ChoiceFormQuestionFeedbackDto.builder()
        .id(id)
        .questionId(questionId)
        .bookmarkDto(bookmarkDto)
        .selectedChoiceIdSet(selectedChoiceIdSet)
        .isRead(isRead)
        .build()
}

fun shortFormQuestionFeedbackDto(
    id: Long = 1L,
    questionId: Long = 0L,
    isRead: Boolean = false,
    bookmarkDto: BookmarkDto = bookmarkDto(),
    replies: List<String> = listOf("제가 고쳐야할점을 알려주세요 질문의", "응답입니다.")
): ShortFormQuestionFeedbackDto {
    return ShortFormQuestionFeedbackDto.builder()
        .id(id)
        .bookmarkDto(bookmarkDto)
        .replyList(replies)
        .isRead(isRead)
        .questionId(questionId)
        .build()
}

fun bookmarkDto(
    isBookmarked: Boolean = false,
    time: Instant = TimeUtil.toInstant(),
): BookmarkDto {
    return BookmarkDto.builder()
        .isBookmarked(isBookmarked)
        .bookmarkedAt(time)
        .build()
}
