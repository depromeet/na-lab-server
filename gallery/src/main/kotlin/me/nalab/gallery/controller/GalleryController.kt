package me.nalab.gallery.controller

import me.nalab.gallery.app.GalleryGetApp
import me.nalab.gallery.app.GalleryPreviewApp
import me.nalab.gallery.app.GalleryRegisterApp
import me.nalab.gallery.controller.request.GalleryRegisterRequest
import me.nalab.gallery.domain.response.GalleriesDto
import me.nalab.gallery.domain.response.GalleryDto
import me.nalab.gallery.domain.response.GalleryPreviewDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/gallerys")
class GalleryController(
    private val galleryGetApp: GalleryGetApp,
    private val galleryPreviewApp: GalleryPreviewApp,
    private val galleryRegisterApp: GalleryRegisterApp,
) {

    @GetMapping("/previews")
    @ResponseStatus(HttpStatus.OK)
    fun getGalleryPreview(@RequestAttribute("logined") targetId: Long): GalleryPreviewDto =
        galleryPreviewApp.findGalleryPreview(targetId)

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun registerGallery(
        @RequestAttribute("logined") targetId: Long,
        @RequestBody request: GalleryRegisterRequest,
    ): GalleryDto {
        return galleryRegisterApp.registerGalleryByTargetId(targetId, request.job)
    }

    @GetMapping("/logins")
    @ResponseStatus(HttpStatus.OK)
    fun getGallery(@RequestAttribute("logined") targetId: Long): GalleryDto =
        galleryGetApp.getGalleryByTargetId(targetId)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getGalleries(
        @RequestParam(name = "job", defaultValue = "all") job: String,
        @RequestParam(name = "page", defaultValue = "0") page: Int,
        @RequestParam(name = "count", defaultValue = "5") count: Int,
        @RequestParam(name = "order-type", defaultValue = "update") orderType: String
    ): GalleriesDto {
        return galleryGetApp.getGalleries(job, page, count, orderType)
    }

}
