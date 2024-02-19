package me.nalab.gallery.integration

import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response

fun getByUserId(userId: Long): ExtractableResponse<Response> {
    return RestAssured.given().log().all()
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .`when`().log().all()
        .get("/v1/gallerys?user-id=$userId")
        .then().log().all()
        .extract();
}
