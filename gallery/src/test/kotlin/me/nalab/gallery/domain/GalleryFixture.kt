package me.nalab.gallery.domain

import me.nalab.core.time.TimeUtil
import java.time.Instant

fun gallery(
    id: Long = 0L,
    targetId: Long = 0L,
    job: Job = Job.OTHERS,
    surveyId: Long = 0L,
    bookmarkedCount: Int = 0,
    updateOrder: Instant = TimeUtil.toInstant(),
): Gallery = Gallery(
    id = id,
    targetId = targetId,
    job = job,
    surveyId = surveyId,
    bookmarkedCount = bookmarkedCount,
    updateOrder = updateOrder,
)
