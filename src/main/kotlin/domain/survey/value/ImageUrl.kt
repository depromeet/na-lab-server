package domain.survey.value

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class ImageUrl(
    @Column(name = "image_url", nullable = false)
    val value: String,
) {

    companion object {
        fun empty(): ImageUrl = ImageUrl("")
    }
}
