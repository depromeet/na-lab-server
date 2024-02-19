package me.nalab.gallery.integration

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.DescribeSpec
import io.restassured.RestAssured
import me.nalab.gallery.TestRoot
import me.nalab.gallery.domain.GalleryRepository
import me.nalab.gallery.domain.gallery
import me.nalab.gallery.domain.response.GalleryResponse
import me.nalab.gallery.domain.survey
import me.nalab.gallery.domain.user
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource

@DisplayName("Gallery 통합테스트의")
@TestPropertySource("classpath:h2.properties")
@ContextConfiguration(classes = [TestRoot::class])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
internal class GalleryIntegrationTest(
    private val galleryRepository: GalleryRepository,
    @LocalServerPort private val port: Int,
) : DescribeSpec({

    beforeEach {
        RestAssured.port = port
        galleryRepository.saveAndFlush(existGallery)
    }

    describe("userId에 해당하는 gallery 조회 api는") {
        context("userId에 해당하는 gallery가 존재한다면,") {

            val expected = GalleryResponse(existGallery)

            it("galleryResponse를 반환한다.") {
                val response = getByUserId(EXIST_USER_ID)

                response.assertOk()
                response.assertGalleryResponse(expected)
            }
        }

        context("userId에 해당하는 gallery가 존재하지 않다면,") {
            val notExistsUserId = 404L
            val expectedMessage = "Cannot find exists gallery by userId \"$notExistsUserId\""

            it("400 Bad Request를 응답한다.") {
                val response = getByUserId(notExistsUserId)

                response.assertBadRequest(expectedMessage)
            }
        }
    }

}) {
    private companion object {
        private const val EXIST_GALLERY_ID = 100L
        private const val EXIST_USER_ID = 101L
        private const val EXIST_SURVEY_ID = 102L

        private val existGallery = gallery(
            EXIST_GALLERY_ID,
            user = user(id = EXIST_USER_ID),
            survey = survey(id = EXIST_SURVEY_ID),
        )
    }

}
