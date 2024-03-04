package me.nalab.gallery.domain.response

data class GalleriesDto(
    val totalPage: Int,
    val galleries: List<GalleryDto>
)
