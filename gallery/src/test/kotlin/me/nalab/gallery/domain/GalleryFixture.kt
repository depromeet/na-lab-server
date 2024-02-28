package me.nalab.gallery.domain

import java.time.Instant

fun gallery(
    id: Long = 0L,
    targetId: Long = 0L,
    job: Job = Job.OTHERS,
    surveyId: Long = 0L,
    bookmarkedCount: Int = 0,
    updateOrder: Instant = Instant.now(),
): Gallery = Gallery(
    id = id,
    targetId = targetId,
    job = job,
    surveyId = surveyId,
    bookmarkedCount = bookmarkedCount,
    updateOrder = updateOrder,
)
