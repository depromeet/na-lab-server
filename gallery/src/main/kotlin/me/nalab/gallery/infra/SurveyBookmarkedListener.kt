package me.nalab.gallery.infra

import me.nalab.gallery.domain.GalleryService
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
    override fun increaseBookmarked(targetId: Long) {
        runCatching {
            galleryService.increaseBookmarkCount(targetId)
        }.recoverCatching {
            when (it is OptimisticLockingFailureException) {
                true -> {
                    waitJitter()
                    increaseBookmarked(targetId)
                }

                false -> throw it
            }
        }
    }

    @Async
    override fun decreaseBookmarked(targetId: Long) {
        runCatching {
            galleryService.decreaseBookmarkCount(targetId)
        }.recoverCatching {
            when (it is OptimisticLockingFailureException) {
                true -> {
                    waitJitter()
                    decreaseBookmarked(targetId)
                }

                false -> throw it
            }
        }
    }


    private fun waitJitter() {
        Thread.sleep(Random.nextLong(500, 1000))
    }
}
