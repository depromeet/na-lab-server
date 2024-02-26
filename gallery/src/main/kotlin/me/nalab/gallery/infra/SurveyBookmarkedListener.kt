package me.nalab.gallery.infra

import me.nalab.gallery.domain.GalleryService
import me.nalab.survey.application.port.out.persistence.bookmark.SurveyBookmarkListenPort
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.stereotype.Service

@Service
class SurveyBookmarkedListener(
    private val galleryService: GalleryService,
): SurveyBookmarkListenPort {

    override fun listenBookmarked(targetId: Long) {
        runCatching {
            galleryService.increaseBookmarkCount(targetId)
        }.recoverCatching {
            when(it is OptimisticLockingFailureException) {
                true -> listenBookmarked(targetId)
                false -> throw it
            }
        }
    }
}
