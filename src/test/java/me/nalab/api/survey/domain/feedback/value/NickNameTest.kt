package me.nalab.api.survey.domain.feedback.value

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.equals.shouldBeEqual

@DisplayName("NickName 클래스의")
internal class NickNameTest : DescribeSpec({

    describe("nextNickName 메소드는") {
        context("이전 이름으로 null 을 입력받으면,") {
            it("A 를 반환한다.") {
                val result = NickName.nextNickName(null)

                result.value shouldBeEqual "A"
            }
        }

        context("이전 이름으로 A를 입력받으면,") {
            it("B 를 반환한다.") {
                val result = NickName.nextNickName("A")

                result.value shouldBeEqual "B"
            }
        }

        context("이전 이름으로 Z를 입력받으면,") {
            it("AA 를 반환한다.") {
                val result = NickName.nextNickName("Z")

                result.value shouldBeEqual "AA"
            }
        }

        context("이전 이름으로 ABC를 입력받으면") {
            it("ABD 를 반환한다.") {
                val result = NickName.nextNickName("ABC")

                result.value shouldBeEqual "ABD"
            }
        }

        context("이전 이름으로 ZZZ를 입력받으면,") {
            it("AAAA를 반환한다.") {
                val result = NickName.nextNickName("ZZZ")

                result.value shouldBeEqual "AAAA"
            }
        }

        context("이전 이름으로 ABZ를 입력받으면,") {
            it("ACA를 반환한다.") {
                val result = NickName.nextNickName("ABZ")

                result.value shouldBeEqual "ACA"
            }
        }
    }
})
