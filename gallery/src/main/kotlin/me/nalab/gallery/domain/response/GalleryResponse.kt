package me.nalab.gallery.domain.response

import com.fasterxml.jackson.annotation.JsonProperty
import me.nalab.gallery.domain.Gallery

data class GalleryResponse(
    val id: String,
    val user: User,
    val survey: Survey,
) {

    constructor(gallery: Gallery) : this(
        id = gallery.id.toString(),
        user = User(
            gallery.userId().toString(),
            gallery.userName(),
            gallery.userPosition(),
        ),
        survey = Survey(
            gallery.surveyId().toString(),
            gallery.surveyFeedbackCount(),
            gallery.surveyFeedbackCount(),
        ),
    )

    data class User(
        val id: String,
        val name: String,
        val position: String,
    )

    data class Survey(
        val id: String,
        @JsonProperty("feedback_count")
        val feedbackCount: Int,
        @JsonProperty("save_count")
        val saveCount: Int,
    )
}
