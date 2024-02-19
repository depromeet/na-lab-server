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
            gallery.userNickname(),
            gallery.userImageUrl(),
        ),
        survey = Survey(
            gallery.surveyId().toString(),
            gallery.surveyFeedbackCount(),
            gallery.surveySaveCount(),
            gallery.feedback(),
            gallery.tendencies()
                .asSequence()
                .map { Survey.Tendency(it.name, it.tendencyCount) }
                .toList(),
        ),
    )

    data class User(
        val id: String,
        val name: String,
        val nickname: String,
        @JsonProperty("image_url")
        val imageUrl: String?,
    )

    data class Survey(
        val id: String,
        @JsonProperty("feedback_count")
        val feedbackCount: Int,
        @JsonProperty("save_count")
        val saveCount: Int,
        val feedback: String,
        val tendencies: List<Tendency>,
    ) {

        data class Tendency(
            val name: String,
            val count: Int,
        )
    }
}
