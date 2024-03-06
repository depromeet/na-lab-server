POST http://nalab-server:8080/v1/oauth/default # Default provider를 통해서 로그인 진행
{
    "nickname": "find_bookmakred_survey1",
    "email": "find_bookmakred_survey1@12345"
}

HTTP 200
[Asserts]
header "Content-type" == "application/json"

jsonpath "$.access_token" exists
jsonpath "$.token_type" exists

[Captures]
token_type_1: jsonpath "$.token_type"
auth_token_1: jsonpath "$.access_token"

##########

POST http://nalab-server:8080/v1/surveys # 발급받은 토큰으로 survey를 생성한다.
Authorization: {{ token_type_1 }} {{ auth_token_1 }}
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
survey_id_1: jsonpath "$.survey_id"

##########

POST http://nalab-server:8080/v1/oauth/default # Default provider를 통해서 로그인 진행
{
    "nickname": "find_bookmakred_survey2",
    "email": "find_bookmakred_survey2@12345"
}

HTTP 200
[Asserts]
header "Content-type" == "application/json"

jsonpath "$.access_token" exists
jsonpath "$.token_type" exists

[Captures]
token_type_2: jsonpath "$.token_type"
auth_token_2: jsonpath "$.access_token"

##########

POST http://nalab-server:8080/v1/surveys # 발급받은 토큰으로 survey를 생성한다.
Authorization: {{ token_type_2 }} {{ auth_token_2 }}
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
survey_id_2: jsonpath "$.survey_id"

##########

POST http://nalab-server:8080/v1/surveys/{{ survey_id_1 }}/bookmarks
Authorization: {{ token_type_1 }} {{ auth_token_1 }}

HTTP 200
[Asserts]
header "Content-type" == "application/json"

jsonpath "$.survey_id" == {{ survey_id_1 }}

##########

POST http://nalab-server:8080/v1/surveys/{{ survey_id_2 }}/bookmarks
Authorization: {{ token_type_1 }} {{ auth_token_1 }}

HTTP 200
[Asserts]
header "Content-type" == "application/json"

jsonpath "$.survey_id" == {{ survey_id_2 }}

##########

GET http://nalab-server:8080/v1/surveys/bookmarks
Authorization: {{ token_type_1 }} {{ auth_token_1 }}

HTTP 200
[Asserts]
header "Content-type" == "application/json"

jsonpath "$.bookmarked_surveys.[0].survey_id" == {{ survey_id_1 }}
jsonpath "$.bookmarked_surveys.[0].nickname" == "find_bookmakred_survey1"

jsonpath "$.bookmarked_surveys.[1].survey_id" == {{ survey_id_2 }}
jsonpath "$.bookmarked_surveys.[1].nickname" == "find_bookmakred_survey2"

##########

GET http://nalab-server:8080/v1/surveys/bookmarks
Authorization: {{ token_type_1 }} {{ auth_token_1 }}

[QueryStringParams]
last-survey-id: {{ survey_id_1 }}
count: 1

HTTP 200
[Asserts]
header "Content-type" == "application/json"

jsonpath "$.bookmarked_surveys.[0].survey_id" == {{ survey_id_2 }}
jsonpath "$.bookmarked_surveys.[0].nickname" == "find_bookmakred_survey2"
