package me.nalab.gallery.integration

import io.kotest.matchers.equality.shouldBeEqualUsingFields
import io.kotest.matchers.shouldBe
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import me.nalab.gallery.domain.response.GalleryResponse

fun ExtractableResponse<Response>.assertGalleryResponse(galleryResponse: GalleryResponse) {
    this.body().`as`(GalleryResponse::class.java) shouldBeEqualUsingFields galleryResponse
}

