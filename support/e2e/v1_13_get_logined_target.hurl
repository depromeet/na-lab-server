POST http://nalab-server:8080/v1/oauth/default # Default provider를 통해서 로그인 진행
{
    "nickname": "devxb",
    "email": "get_logined_target@naver.com"
}

HTTP 200
[Asserts]
header "Content-type" == "application/json"

jsonpath "$.access_token" exists
jsonpath "$.token_type" exists

[Captures]
token_type: jsonpath "$.token_type"
auth_token: jsonpath "$.access_token"

#######

GET http://nalab-server:8080/v1/users/logins # Token에 해당하는 유저 조회
Authorization: {{ token_type }} {{ auth_token }}

HTTP 200
[Asserts]
header "Content-type" == "application/json"

jsonpath "$.target_id" exists
jsonpath "$.nickname" == "devxb"
jsonpath "$.email" == "get_logined_target@naver.com"
