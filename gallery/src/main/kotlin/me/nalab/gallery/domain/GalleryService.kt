package me.nalab.gallery.domain

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class GalleryService(
    private val galleryRepository: GalleryRepository,
) {

    fun getGalleryByTargetId(targetId: Long): Gallery {
        return galleryRepository.findByTargetIdOrNull(targetId)
            ?: throw IllegalArgumentException("targetId \"$targetId\" 에 해당하는 Gallery를 찾을 수 없습니다.")
    }

    @Transactional
    fun registerGallery(gallery: Gallery): Gallery {
        require(galleryRepository.findByTargetIdOrNull(gallery.getTargetId()) == null) {
            "target \"${gallery.getTargetId()}\" 이 이미 Gallery에 등록되어있습니다."
        }

        return galleryRepository.save(gallery)
    }

    @Transactional
    fun increaseBookmarkCount(targetId: Long) {
        galleryRepository.findByTargetIdOrNull(targetId)?.increaseBookmarkedCount()
    }

    fun getGalleries(job: String, pageable: Pageable): Page<Gallery> {
        val jobs = when (job.uppercase()) {
            "ALL" -> Job.entries.toList()
            else -> listOf(Job.valueOf(job.uppercase()))
        }

        return galleryRepository.findGalleries(jobs, pageable)
    }
}
