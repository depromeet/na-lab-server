package me.nalab.gallery.integration

import io.kotest.matchers.shouldBe
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response

fun ExtractableResponse<Response>.assertOk() = this.statusCode() shouldBe 200

fun ExtractableResponse<Response>.assertBadRequest(message: String) {
    this.statusCode() shouldBe 400
    this.body().jsonPath().getString("response_message") shouldBe message
}
