package me.nalab.gallery.controller

import com.fasterxml.jackson.annotation.JsonProperty

data class ErrorTemplate(
    @JsonProperty("response_message")
    val message: String,
) {
    constructor(exception: Exception) : this(exception.message ?: exception::class.simpleName!!)
}
