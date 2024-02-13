package me.nalab.gallery.domain

import io.kotest.assertions.throwables.shouldThrowMessage
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.equality.shouldBeEqualUsingFields
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration

@DataJpaTest
@ContextConfiguration(
    classes = [
        GalleryService::class,
    ]
)
@DisplayName("GalleryService 클래스의")
@EnableAutoConfiguration
class GalleryServiceTest(
    private val galleryService: GalleryService,
    private val galleryRepository: GalleryRepository,
) : DescribeSpec({

    describe("getByUserId 메소드는") {
        context("userId에 해당하는 Gallery 가 존재한다면,") {
            val userId = 100L
            val gallery = gallery(user = user(id = userId))
            galleryRepository.saveAndFlush(gallery)

            it("Gallery 를 반환한다.") {
                val result = galleryService.getByUserId(userId)

                result shouldBeEqualUsingFields gallery
            }
        }

        context("userId에 해당하는 Gallery 가 없다면,") {
            val notExistUserId = 404L

            it("IllegalArgumentException을 던진다.") {
                shouldThrowMessage("Cannot find exists gallery by userId \"$notExistUserId\"") {
                    galleryService.getByUserId(notExistUserId)
                }
            }
        }
    }
}) {
}
