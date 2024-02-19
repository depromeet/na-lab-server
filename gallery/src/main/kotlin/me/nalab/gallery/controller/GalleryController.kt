package me.nalab.gallery.controller

import me.nalab.gallery.domain.GalleryService
import me.nalab.gallery.domain.response.GalleryResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class GalleryController(
    private val galleryService: GalleryService,
) {

    @GetMapping("/v1/gallerys")
    @ResponseStatus(HttpStatus.OK)
    fun getGalleryByUserId(@RequestParam("user-id") userId: Long): GalleryResponse =
        galleryService.getByUserId(userId)

    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalArgumentException(exception: IllegalArgumentException): ErrorTemplate =
        ErrorTemplate(exception)
}
