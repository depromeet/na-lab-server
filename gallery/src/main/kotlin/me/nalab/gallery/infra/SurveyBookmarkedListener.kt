package me.nalab.gallery.infra

import me.nalab.gallery.domain.GalleryService
import me.nalab.survey.application.common.survey.dto.TargetDto
import me.nalab.survey.application.port.out.persistence.bookmark.SurveyBookmarkListenPort
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class SurveyBookmarkedListener(
    private val galleryService: GalleryService,
) : SurveyBookmarkListenPort {

    @Async
    override fun listenBookmarked(target: TargetDto) {
        runCatching {
            galleryService.setBookmarkCount(target.id, target.bookmarkedSurveys.count())
        }.recoverCatching {
            when (it is OptimisticLockingFailureException) {
                true -> {
                    waitJitter()
                    listenBookmarked(target)
                }

                false -> throw it
            }
        }
    }

    private fun waitJitter() {
        Thread.sleep(Random.nextLong(500, 1000))
    }
}
