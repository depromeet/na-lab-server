POST http://nalab-server:8080/v1/oauth/default # 로그인
{
    "nickname" : "luffy",
    "email" : "feedback_find_1@gmail.com"
}

HTTP 200
[Asserts]
header "Content-type" == "application/json"

jsonpath "$.token_type" exists
jsonpath "$.access_token" exists

[Captures]
token_type: jsonpath "$.token_type"
access_token: jsonpath "$.access_token"

##########

POST http://nalab-server:8080/v1/surveys # survey 생성 [객관식-tendency, 주관식-strength, 객관식-custom, 주관식-custom]
Authorization: {{ token_type }} {{ access_token }}
{
    "question_count": 4,
    "question": [
        {
            "type": "choice",
            "form_type": "tendency",
            "title": "동료들이 생각한 나의 이미지",
            "choices": [
                {
                    "content": "사교적인",
                    "order": 1
                },
                {
                    "content": "적극적인",
                    "order": 2
                },
                {
                    "content": "리더십있는",
                    "order": 3
                },
                {
                    "content": "밝은",
                    "order": 4
                }
            ],
            "max_selectable_count": 4,
            "order": 1
        },
        {
            "type": "short",
            "form_type": "strength",
            "title": "협업을 하면서 느꼈던 나만의 장점이 있나요?",
            "order": 2
        },
        {
            "type": "choice",
            "form_type": "custom",
            "title": "저는 UI, UI, GUI 중에 어떤 분야를 가장 잘하는 것 같나요?",
            "choices": [
                {
                    "content": "UI",
                    "order": 1
                },
                {
                    "content": "UI",
                    "order": 2
                }
            ],
            "max_selectable_count": 1,
            "order": 3
        },
        {
            "type": "short",
            "form_type": "custom",
            "title": "마지막으로 저에게 조언하고 싶은 것이 있나요?",
            "order": 4
        }
    ]
}

HTTP 201
[Asserts]
header "Content-type" == "application/json"

jsonpath "$.survey_id" exists

[Captures]
survey_id: jsonpath "$.survey_id"

##########

GET http://nalab-server:8080/v1/surveys/{{ survey_id }} # feedback을 생성하기 위한 survey 조회 및 id값들 저장

HTTP 200
[Asserts]
header "Content-type" == "application/json"

[Captures]
choice_tendency_question_id: jsonpath "$.question.[0].question_id"
choice_tendency_choice_id: jsonpath "$.question.[0].choices.[0].choice_id"
short_strength_question_id: jsonpath "$.question.[1].question_id"
choice_custom_question_id: jsonpath "$.question.[2].question_id"
choice_custom_choice_id: jsonpath "$.question.[2].choices.[0].choice_id"
short_custom_question_id: jsonpath "$.question.[3].question_id"
formtype_tendency: jsonpath "$.question.[0].form_type"
formtype_strength: jsonpath "$.question.[1].form_type"
formtype_custom: jsonpath "$.question.[2].form_type"


##########

POST http://nalab-server:8080/v1/feedbacks # feedback 생성

[QueryStringParams]
survey-id: {{ survey_id }}

{
    "reviewer": {
        "collaboration_experience": true,
        "position": "developer"
    },
    "question_feedback": [
        {
            "question_id": {{ choice_tendency_question_id }},
            "type": "choice",
            "choices": [
                {{ choice_tendency_choice_id }}
            ]
        },
        {
            "question_id": {{ short_strength_question_id }},
            "type": "short",
            "reply": [
                "예진이는 개발 관련 지식을 조금 더 공부해봐도\n좋을 것 같아!",
                "안녕"
            ]
        },
        {
            "question_id": {{ choice_custom_question_id }},
            "type": "choice",
            "choices": [
                {{ choice_custom_choice_id }}
            ]
        },
        {
            "question_id": {{ short_custom_question_id }},
            "type": "short",
            "reply": [
                "예진이는 개발 관련 지식을 조금 더 공부해봐도\n좋을 것 같아!",
                "안녕"
            ]
        }
    ]
}

HTTP 201
[Asserts]

##########

GET http://nalab-server:8080/v1/feedbacks # 질문 폼의 모든 피드백 조회 및 개별 피드백 조회를 위한 feedback_id 저장
Authorization: {{ token_type }} {{ access_token }}

[QueryStringParams]
survey-id: {{ survey_id }}

HTTP 200
[Asserts]
header "Content-type" == "application/json"

jsonpath "$.question_feedback" count == 4
jsonpath "$.question_feedback.[0].question_id" exists
jsonpath "$.question_feedback.[0].order" exists
jsonpath "$.question_feedback.[0].type" exists
jsonpath "$.question_feedback.[0].form_type" matches "tendency|strength|custom"
jsonpath "$.question_feedback.[0].title" exists
jsonpath "$.question_feedback.[0].feedbacks" count == 1
jsonpath "$.question_feedback.[0].feedbacks.[0].feedback_id" exists
jsonpath "$.question_feedback.[0].feedbacks.[0].form_question_feedback_id" exists
jsonpath "$.question_feedback.[0].feedbacks.[0].created_at" exists
jsonpath "$.question_feedback.[0].feedbacks.[0].is_read" exists
jsonpath "$.question_feedback.[0].feedbacks.[0].bookmark.is_bookmarked" exists
jsonpath "$.question_feedback.[0].feedbacks.[0].bookmark.bookmarked_at" exists
jsonpath "$.question_feedback.[0].feedbacks.[0].reviewer.reviewer_id" exists
jsonpath "$.question_feedback.[0].feedbacks.[0].reviewer.nickname" matches "^[A-Z].*"
jsonpath "$.question_feedback.[0].feedbacks.[0].reviewer.collaboration_experience" exists
jsonpath "$.question_feedback.[0].feedbacks.[0].reviewer.position" matches "designer|product-manager|developer|other"

[Captures]
feedback_id: jsonpath "$.question_feedback.[0].feedbacks.[0].feedback_id"

##########

GET http://nalab-server:8080/v1/feedbacks/{{ feedback_id }} # 개별 피드백 응답 조회
Authorization: {{ token_type }} {{ access_token }}

HTTP 200
[Asserts]
header "Content-type" == "application/json"

jsonpath "$.feedback_id" exists
jsonpath "$.created_at" exists
jsonpath "$.reviewer.nickname" matches "^[A-Z].*"
jsonpath "$.reviewer.collaboration_experience" exists
jsonpath "$.reviewer.position" matches "designer|product-manager|developer|other"
jsonpath "$.question" count == 4
jsonpath "$.question[0].question_id" exists
jsonpath "$.question[0].type" matches "short|choice"
jsonpath "$.question[0].form_type" matches "tendency|strength|custom"
jsonpath "$.question[0].title" exists
jsonpath "$.question[0].order" exists
jsonpath "$.question[0].is_read" exists

##########

POST http://nalab-server:8080/v1/feedbacks # 추가 feedback 생성

[QueryStringParams]
survey-id: {{ survey_id }}

{
    "reviewer": {
        "collaboration_experience": false,
        "position": "designer"
    },
    "question_feedback": [
        {
            "question_id": {{ choice_tendency_question_id }},
            "type": "choice",
            "choices": [
                {{ choice_tendency_choice_id }}
            ]
        },
        {
            "question_id": {{ short_strength_question_id }},
            "type": "short",
            "reply": [
                "예진이는 개발 관련 지식을 조금 더 공부해봐도\n좋을 것 같아!",
                "안녕"
            ]
        },
        {
            "question_id": {{ choice_custom_question_id }},
            "type": "choice",
            "choices": [
                {{ choice_custom_choice_id }}
            ]
        },
        {
            "question_id": {{ short_custom_question_id }},
            "type": "short",
            "reply": [
                "예진이는 개발 관련 지식을 조금 더 공부해봐도\n좋을 것 같아!",
                "안녕"
            ]
        }
    ]
}

HTTP 201
[Asserts]

##########

GET http://nalab-server:8080/v1/reviewers # 질문폼의 모든 리뷰어 목록 조회
Authorization: {{ token_type }} {{ access_token }}

[QueryStringParams]
survey-id: {{ survey_id }}

HTTP 200
[Asserts]
header "Content-type" == "application/json"

jsonpath "$.feedbacks" count == 2
jsonpath "$.feedbacks[0].created_at" exists
jsonpath "$.feedbacks[0].is_read" exists
jsonpath "$.feedbacks[0].reviewer.nickname" matches "^[A-Z].*"
jsonpath "$.feedbacks[0].reviewer.collaboration_experience" exists
jsonpath "$.feedbacks[0].reviewer.position" matches "designer|product-manager|developer|other"
jsonpath "$.feedbacks[1].created_at" exists
jsonpath "$.feedbacks[1].is_read" exists
jsonpath "$.feedbacks[1].reviewer.nickname" matches "^[A-Z].*"
jsonpath "$.feedbacks[1].reviewer.collaboration_experience" exists
jsonpath "$.feedbacks[1].reviewer.position" matches "designer|product-manager|developer|other"

##########

GET http://nalab-server:8080/v1/reviewers/summary # 질문폼의 모든 리뷰어 목록 조회
Authorization: {{ token_type }} {{ access_token }}

[QueryStringParams]
survey-id: {{ survey_id }}

HTTP 200
[Asserts]
header "Content-type" == "application/json"

jsonpath "$.collaboration_experience.yes" == 1
jsonpath "$.collaboration_experience.no" == 1
jsonpath "$.position.developer" == 1
jsonpath "$.position.designer" == 1
jsonpath "$.position.pm" == 0
jsonpath "$.position.others" == 0

##########

GET http://nalab-server:8080/v1/feedbacks/summary # 질문폼의 전체 피드백 수와 읽지 않은 피드백의 수 조회
Authorization: {{ token_type }} {{ access_token }}

[QueryStringParams]
survey-id: {{ survey_id }}

HTTP 200
[Asserts]
header "Content-type" == "application/json"

jsonpath "$.all_feedback_count" == 2
jsonpath "$.new_feedback_count" == 1

##########

GET http://nalab-server:8080/v2/feedbacks # 생성된 survey와 feedback 2개를 바탕으로, 질문폼의 타입 "tendency"에 해당하는 피드백을 조회한다

[QueryStringParams]
survey-id: {{ survey_id }}
form-type: {{ formtype_tendency }}

HTTP 200
[Asserts]
header "Content-type" == "application/json"

jsonpath "$.question_feedback" count == 1
jsonpath "$.question_feedback.[0].question_id" exists
jsonpath "$.question_feedback.[0].order" exists
jsonpath "$.question_feedback.[0].type" matches "choice"
jsonpath "$.question_feedback.[0].form_type" matches "tendency"
jsonpath "$.question_feedback.[0].title" exists

jsonpath "$.question_feedback.[0].choices" count == 4
jsonpath "$.question_feedback.[0].choices.[0].order" exists
jsonpath "$.question_feedback.[0].choices.[0].content" exists
jsonpath "$.question_feedback.[0].choices.[0].choice_id" exists
jsonpath "$.question_feedback.[0].choices.[0].selected_count" == 2
jsonpath "$.question_feedback.[0].choices.[1].order" exists
jsonpath "$.question_feedback.[0].choices.[1].content" exists
jsonpath "$.question_feedback.[0].choices.[1].choice_id" exists
jsonpath "$.question_feedback.[0].choices.[1].selected_count" == 0
jsonpath "$.question_feedback.[0].choices.[2].order" exists
jsonpath "$.question_feedback.[0].choices.[2].content" exists
jsonpath "$.question_feedback.[0].choices.[2].choice_id" exists
jsonpath "$.question_feedback.[0].choices.[2].selected_count" == 0
jsonpath "$.question_feedback.[0].choices.[3].order" exists
jsonpath "$.question_feedback.[0].choices.[3].content" exists
jsonpath "$.question_feedback.[0].choices.[3].choice_id" exists
jsonpath "$.question_feedback.[0].choices.[3].selected_count" == 0

##########

GET http://nalab-server:8080/v2/feedbacks # 생성된 survey와 feedback 2개를 바탕으로, 질문폼의 타입 "strength"에 해당하는 피드백을 조회한다

[QueryStringParams]
survey-id: {{ survey_id }}
form-type: {{ formtype_strength }}

HTTP 200
[Asserts]
header "Content-type" == "application/json"

jsonpath "$.question_feedback" count == 1
jsonpath "$.question_feedback.[0].question_id" exists
jsonpath "$.question_feedback.[0].order" exists
jsonpath "$.question_feedback.[0].type" matches "short"
jsonpath "$.question_feedback.[0].form_type" matches "strength"
jsonpath "$.question_feedback.[0].title" exists

jsonpath "$.question_feedback.[0].feedbacks" count == 2
jsonpath "$.question_feedback.[0].feedbacks.[0].feedback_id" exists
jsonpath "$.question_feedback.[0].feedbacks.[0].created_at" exists
jsonpath "$.question_feedback.[0].feedbacks.[0].reply" exists
jsonpath "$.question_feedback.[0].feedbacks.[1].feedback_id" exists
jsonpath "$.question_feedback.[0].feedbacks.[1].created_at" exists
jsonpath "$.question_feedback.[0].feedbacks.[1].reply" exists

##########

GET http://nalab-server:8080/v2/feedbacks # 생성된 survey와 feedback 2개를 바탕으로, 질문폼의 타입 "custom"에 해당하는 피드백을 조회한다

[QueryStringParams]
survey-id: {{ survey_id }}
form-type: {{ formtype_custom }}

HTTP 200
[Asserts]
header "Content-type" == "application/json"

jsonpath "$.question_feedback" count == 2

jsonpath "$.question_feedback.[0].question_id" exists
jsonpath "$.question_feedback.[0].order" exists
jsonpath "$.question_feedback.[0].type" matches "choice"
jsonpath "$.question_feedback.[0].form_type" matches "custom"
jsonpath "$.question_feedback.[0].title" exists
jsonpath "$.question_feedback.[0].choices" count == 2
jsonpath "$.question_feedback.[0].choices.[0].order" exists
jsonpath "$.question_feedback.[0].choices.[0].content" exists
jsonpath "$.question_feedback.[0].choices.[0].choice_id" exists
jsonpath "$.question_feedback.[0].choices.[0].selected_count" == 2
jsonpath "$.question_feedback.[0].choices.[1].order" exists
jsonpath "$.question_feedback.[0].choices.[1].content" exists
jsonpath "$.question_feedback.[0].choices.[1].choice_id" exists
jsonpath "$.question_feedback.[0].choices.[1].selected_count" == 0

jsonpath "$.question_feedback.[1].question_id" exists
jsonpath "$.question_feedback.[1].order" exists
jsonpath "$.question_feedback.[1].type" matches "short"
jsonpath "$.question_feedback.[1].form_type" matches "custom"
jsonpath "$.question_feedback.[1].title" exists
jsonpath "$.question_feedback.[1].feedbacks" count == 2
jsonpath "$.question_feedback.[1].feedbacks.[0].feedback_id" exists
jsonpath "$.question_feedback.[1].feedbacks.[0].created_at" exists
jsonpath "$.question_feedback.[1].feedbacks.[0].reply" exists
jsonpath "$.question_feedback.[1].feedbacks.[1].feedback_id" exists
jsonpath "$.question_feedback.[1].feedbacks.[1].created_at" exists
jsonpath "$.question_feedback.[1].feedbacks.[1].reply" exists
