POST http://nalab-server:8080/v1/oauth/default # Default provider를 통해서 로그인 진행
{
    "nickname": "bookmark_survey",
    "email": "bookmark_survey@kakao.com"
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

GET http://nalab-server:8080/v1/surveys/{{ survey_id }} # 생성된 survey를 조회한다.

HTTP 200
[Asserts]
header "Content-type" == "application/json"

jsonpath "$.survey_id" exists

jsonpath "$.target.id" exists
jsonpath "$.target.nickname" == "bookmark_survey"

jsonpath "$.question_count" == 2
jsonpath "$.question.[0].question_id" exists
jsonpath "$.question.[0].type" == "choice"
jsonpath "$.question.[0].form_type" == "tendency"
jsonpath "$.question.[0].title" == "저는 UI, UI, GUI 중에 어떤 분야를 가장 잘하는 것 같나요?"
jsonpath "$.question.[0].order" == 1
jsonpath "$.question.[0].max_selectable_count" == 1
jsonpath "$.question.[0].choices.[0].choice_id" exists
jsonpath "$.question.[0].choices.[0].content" == "UI"
jsonpath "$.question.[0].choices.[0].order" == 1
jsonpath "$.question.[0].choices.[1].choice_id" exists
jsonpath "$.question.[0].choices.[1].content" == "UX"
jsonpath "$.question.[0].choices.[1].order" == 2
jsonpath "$.question.[0].choices.[2].choice_id" exists
jsonpath "$.question.[0].choices.[2].content" == "GUI"
jsonpath "$.question.[0].choices.[2].order" == 3
jsonpath "$.question.[1].question_id" exists
jsonpath "$.question.[1].type" == "short"
jsonpath "$.question.[1].form_type" == "strength"
jsonpath "$.question.[1].title" == "저는 UX, UI, GUI 중에 어떤 분야에 더 강점이 있나요?"
jsonpath "$.question.[1].order" == 2

[Captures]
target_id: jsonpath "$.target.id"

##########

POST http://nalab-server:8080/v1/surveys/{{ survey_id }}/bookmarks
Authorization: {{ token_type }} {{ auth_token }}

HTTP 200
[Asserts]
header "Content-type" == "application/json"

jsonpath "$.survey_id" == {{ survey_id }}
