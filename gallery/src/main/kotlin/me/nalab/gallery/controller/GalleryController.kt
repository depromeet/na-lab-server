package me.nalab.gallery.controller

import me.nalab.gallery.app.GalleryPreviewApp
import me.nalab.gallery.domain.response.GalleryPreviewDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/gallerys")
class GalleryController(
    private val galleryPreviewApp: GalleryPreviewApp,
) {

    @GetMapping("/previews")
    @ResponseStatus(HttpStatus.OK)
    fun getGalleryPreview(@RequestAttribute("logined") targetId: Long): GalleryPreviewDto =
        galleryPreviewApp.getGalleryPreview(targetId)

}
