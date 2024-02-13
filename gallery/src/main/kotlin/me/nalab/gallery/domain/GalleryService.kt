package me.nalab.gallery.domain

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class GalleryService(
    private val galleryRepository: GalleryRepository,
) {

    fun getByUserId(userId: Long): Gallery {
        return galleryRepository.findByUserId(userId)
            ?: throw IllegalArgumentException("Cannot find exists gallery by userId \"$userId\"")
    }
}
