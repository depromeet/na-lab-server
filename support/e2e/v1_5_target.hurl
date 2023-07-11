POST http://nalab-server:8080/v1/oauth/default # 회원가입을 한다
{
    "nickname": "sujin",
    "email": "sujin@email.com"
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

POST http://nalab-server:8080/v1/surveys # 발급받은 토큰으로 survey를 생성한다
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

PATCH http://nalab-server:8080/v1/users # 로그인된 타겟의 정보를 수정한다
Authorization: {{ token_type }} {{ auth_token }}
{
    "position": "BE developer"
}

HTTP 200
[Asserts]

##########

GET http://nalab-server:8080/v1/users # survey-id를 이용해 수정된 타겟의 정보를 확인한다

[QueryStringParams]
survey-id: {{ survey_id }}

HTTP 200
[Asserts]
header "Content-type" == "application/json"

jsonpath "$.target_id" exists
jsonpath "$.nickname" matches "sujin"
jsonpath "$.position" matches "BE developer"
