package me.nalab.gallery.domain.response

import com.fasterxml.jackson.annotation.JsonProperty
import me.nalab.gallery.domain.Job

data class GalleryPreviewDto(
    val target: Target,
    val survey: Survey,
) {

    data class Target(
        @JsonProperty("target_id")
        val targetId: String,
        val nickname: String,
        val position: String?,
        val job: Job = Job.OTHERS,
        @JsonProperty("image_url")
        val imageUrl: String = "empty_image",
    )

    data class Survey(
        @JsonProperty("survey_id")
        val surveyId: String,
        @JsonProperty("feedback_count")
        val feedbackCount: Int,
        @JsonProperty("bookmarked_count")
        val bookmarkedCount: Int,
        val feedbacks: List<String>,
        val tendencies: List<Tendency>,
    ) {
        data class Tendency(
            val name: String,
            val count: Int,
        )
    }

}
