package me.nalab.gallery.domain

import me.nalab.gallery.domain.response.GalleryPreviewDto

fun galleryPreviewDto(
    target: GalleryPreviewDto.Target = galleryPreviewDtoTarget(),
    survey: GalleryPreviewDto.Survey = galleryPreviewDtoSurvey(),
): GalleryPreviewDto {
    return GalleryPreviewDto(
        target = target,
        survey = survey,
    )
}

fun galleryPreviewDtoTarget(
    targetId: String = "0",
    nickname: String = "이준영",
    position: String = "백엔드 엔지니어",
): GalleryPreviewDto.Target {
    return GalleryPreviewDto.Target(
        targetId = targetId,
        nickname = nickname,
        position = position,
    )
}

fun galleryPreviewDtoSurvey(
    surveyId: String = "0",
    feedbackCount: Int = 1,
    bookmarkedCount: Int = 1,
    feedbacks: List<String> = listOf("제가 고쳐야할점을 알려주세요 질문의", "응답입니다."),
    tendencies: List<GalleryPreviewDto.Survey.Tendency> = listOf(galleryPreviewDtoSurveyTendency()),
): GalleryPreviewDto.Survey {
    return GalleryPreviewDto.Survey(
        surveyId = surveyId,
        feedbackCount = feedbackCount,
        bookmarkedCount = bookmarkedCount,
        feedbacks = feedbacks,
        tendencies = tendencies,
    )
}

fun galleryPreviewDtoSurveyTendency(
    name: String = "경청하는",
    count: Int = 1,
): GalleryPreviewDto.Survey.Tendency {
    return GalleryPreviewDto.Survey.Tendency(
        name = name,
        count = count,
    )
}
