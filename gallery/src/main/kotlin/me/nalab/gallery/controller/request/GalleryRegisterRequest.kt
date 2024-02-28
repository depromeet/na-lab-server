package me.nalab.gallery.controller.request

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class GalleryRegisterRequest @JsonCreator constructor(
    @JsonProperty("job")
    val job: String,
)
