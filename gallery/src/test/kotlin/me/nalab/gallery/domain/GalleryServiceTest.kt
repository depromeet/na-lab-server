package me.nalab.gallery.domain

import io.kotest.assertions.throwables.shouldThrowMessage
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.equality.shouldBeEqualUsingFields
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.context.ContextConfiguration

@DataJpaTest
@EnableJpaRepositories
@EntityScan(value = ["me.nalab.core.data", "me.nalab.gallery.domain"])
@DisplayName("GalleryService 클래스의")
@ContextConfiguration(classes = [GalleryService::class])
internal class GalleryServiceTest(
    private val galleryService: GalleryService,
) : DescribeSpec({

    beforeSpec {
        galleryService.registerGallery(gallery(id = EXIST_GALLERY_ID, targetId = EXIST_TARGET_ID, surveyId = EXIST_SURVEY_ID))
    }

    describe("registerGallery 메소드는") {
        context("Gallery에 등록되지 않은 target의 Gallery를 입력받으면,") {
            val gallery = gallery(targetId = NOT_EXIST_TARGET_ID)

            it("Gallery를 저장하고 반환한다.") {
                val result = galleryService.registerGallery(gallery)

                result shouldBeEqualUsingFields gallery
            }
        }

        context("이미 Gallery에 등록된 target의 Gallery를 입력받으면,") {
            val existGallery = gallery(targetId = EXIST_TARGET_ID)

            it("IllegalArgumentException을 던진다.") {
                shouldThrowMessage("target \"$EXIST_GALLERY_ID\" 이 이미 Gallery에 등록되어있습니다.") {
                    galleryService.registerGallery(existGallery)
                }
            }
        }
    }
}) {

    companion object {
        private const val NOT_EXIST_TARGET_ID = 1L
        private const val EXIST_GALLERY_ID = 100L
        private const val EXIST_TARGET_ID = 100L
        private const val EXIST_SURVEY_ID = 100L
    }
}
