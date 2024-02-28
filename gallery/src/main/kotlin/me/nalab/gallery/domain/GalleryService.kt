package me.nalab.gallery.domain

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GalleryService(
    private val galleryRepository: GalleryRepository,
) {

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
}
