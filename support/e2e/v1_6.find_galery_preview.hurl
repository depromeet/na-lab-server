POST http://nalab-server:8080/v1/oauth/default # Default provider를 통해서 로그인 진행
{
    "nickname": "find_gallery",
    "email": "hello@1234567"
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

GET http://nalab-server:8080/v1/gallerys/previews # 생성된 유저의 Gallery Preview 조회
Authorization: {{ token_type }} {{ auth_token }}

HTTP 200
[Asserts]
header "Content-type" == "application/json"

jsonpath "$.target.target_id" exists
jsonpath "$.target.nickname" == "find_gallery"
jsonpath "$.target.position" == null
jsonpath "$.target.job" == "OTHER"
jsonpath "$.target.image_url" == "empty_image"

jsonpath "$.survey.survey_id" == {{ survey_id }}
jsonpath "$.survey.feedback_count" == 1
jsonpath "$.survey.bookmarked_count" == 0
jsonpath "$.survey.feedbacks.[0]" == "Hello world"
jsonpath "$.survey.tendencies.[0].name" == "UI"
jsonpath "$.survey.tendencies.[0].count" == 1
