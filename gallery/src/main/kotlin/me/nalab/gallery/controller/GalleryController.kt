package me.nalab.gallery.controller

import me.nalab.gallery.domain.GalleryService
import me.nalab.gallery.domain.response.GalleryResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class GalleryController(
    private val galleryService: GalleryService,
) {

    @GetMapping("/gallerys")
    fun getGalleryByUserId(@RequestParam("user-id") userId: Long): GalleryResponse =
        galleryService.getByUserId(userId)
}
