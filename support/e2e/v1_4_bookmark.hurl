POST http://nalab-server:8080/v1/oauth/default # Default provider를 통해서 로그인 진행
{
    "nickname": "devxb",
    "email": "hello@12345"
}

HTTP 200
[Asserts]
header "Content-type" == "application/json"

jsonpath "$.access_token" exists
jsonpath "$.token_type" exists

[Captures]
token_type: jsonpath "$.token_type"
auth_token: jsonpath "$.access_token"

##########

POST http://nalab-server:8080/v1/surveys # 발급받은 토큰으로 survey를 생성한다.
Authorization: {{ token_type }} {{ auth_token }}
{
    "question_count": 2,
    "question": [
        {
            "type": "choice",
            "form_type": "tendency",
            "title": "저는 UI, UI, GUI 중에 어떤 분야를 가장 잘하는 것 같나요?",
            "choices": [
                {
                    "content": "UI",
                    "order": 1
                },
                {
                    "content": "UX",
                    "order": 2
                },
                {
                    "content": "GUI",
                    "order": 3
                }
            ],
            "max_selectable_count": 1,
            "order": 1
        },
        {
            "type": "short",
            "form_type": "strength",
            "title": "저는 UX, UI, GUI 중에 어떤 분야에 더 강점이 있나요?",
            "order": 2
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

GET http://nalab-server:8080/v1/surveys/{{ survey_id }} # 생성된 survey를 조회하고, feedback을 남기기 위해 id를 저장한다.

HTTP 200
[Asserts]
header "Content-type" == "application/json"

[Captures]
tendency_question_id: jsonpath "$.question.[0].question_id"
tendency_question_choice_id: jsonpath "$.question.[0].choices.[0].choice_id"
strength_question_id: jsonpath "$.question.[1].question_id"

##########

POST http://nalab-server:8080/v1/feedbacks # 생성된 survey에 feedback을 남긴다.

[QueryStringParams]
survey-id: {{ survey_id }}

{
    "reviewer": {
        "collaboration_experience": true,
        "position": "pm"
    },
    "question_feedback": [
        {
            "question_id": {{ tendency_question_id }},
            "type": "choice",
            "choices": [
                {{ tendency_question_choice_id }}
            ]
        },
        {
            "question_id": {{ strength_question_id }},
            "type": "short",
            "reply": [
                "Hello world"
            ]
        }
    ]
}

HTTP 201
[Asserts]

##########

GET http://nalab-server:8080/v1/feedbacks # 북마크를 위해 feedback id 저장
Authorization: {{ token_type }} {{ access_token }}

[QueryStringParams]
survey-id: {{ survey_id }}

HTTP 200
[Asserts]
header "Content-type" == "application/json"

[Captures]
feedback_id: jsonpath "$.question_feedback.[0].feedbacks.[0].feedback_id"

##########

PATCH /v1/feedbacks/bookmarks # 북마크를 진행한다.
Authorization: {{ token_type }} {{ auth_token }}

[QueryStringParams]
form-question-feedback-id: {{form_question_feedback_id}}

##########

GET /v1/feedbacks/bookmarks # 북마크된 피드백을 조회하면, 북마크된 피드백이 1개 조회된다.

[QueryStringParams]
survey-id: {{survey_id}}

HTTP 200
[Asserts]
header "Content-type" == "application/json"

jsonpath "$.question_feedback" count == 1
jsonpath "$.question_feedback.[0].feedbacks.[0].bookmark.is_bookmarked" == true

##########
PATCH /v1/feedbacks/bookmarks # 북마크를 취소한다.
Authorization: {{ token_type }} {{ auth_token }}

[QueryStringParams]
form-question-feedback-id: {{form_question_feedback_id}}

##########

GET /v1/feedbacks/bookmarks # 북마크된 피드백을 조회하면, 북마크된 피드백이 0개 조회된다.

[QueryStringParams]
survey-id: {{survey_id}}

HTTP 200
[Asserts]
header "Content-type" == "application/json"

jsonpath "$.question_feedback" count == 0
