package me.nalab.gallery.controller

import com.fasterxml.jackson.annotation.JsonProperty

class ErrorTemplate(
    @JsonProperty("response_messages")
    private val message: String,
) {
    companion object {
        fun of(message: String): ErrorTemplate {
            return ErrorTemplate(message)
        }
    }
}

