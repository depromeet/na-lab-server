POST http://localhost:8080/v1/oauth/default # 로그인
{
    "nickname" : "luffy",
    "email" : "test@gmail.com"
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

POST http://localhost:8080/v1/surveys # survey 생성 [객관식-tendency, 주관식-strength, 객관식-custom, 주관식-custom]
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

GET http://localhost:8080/v1/surveys/{{ survey_id }} # feedback을 생성하기 위한 survey 조회 및 id값들 저장

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

##########

POST http://localhost:8080/v1/feedbacks # feedback 생성

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

GET http://localhost:8080/v1/feedbacks # 질문 폼의 모든 피드백 조회 및 개별 피드백 조회를 위한 feedback_id 저장
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
jsonpath "$.question_feedback.[0].feedbacks.[0].created_at" exists
jsonpath "$.question_feedback.[0].feedbacks.[0].is_read" exists
jsonpath "$.question_feedback.[0].feedbacks.[0].reviewer.reviewer_id" exists
jsonpath "$.question_feedback.[0].feedbacks.[0].reviewer.nickname" exists
jsonpath "$.question_feedback.[0].feedbacks.[0].reviewer.collaboration_experience" exists
jsonpath "$.question_feedback.[0].feedbacks.[0].reviewer.position" matches "designer|product-manager|developer|other"

[Captures]
feedback_id: jsonpath "$.question_feedback.[0].feedbacks.[0].feedback_id"

##########

GET http://localhost:8080/v1/feedbacks/{{ feedback_id }} # 개별 피드백 응답 조회
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
