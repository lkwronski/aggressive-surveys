#!/bin/sh

# shellcheck disable=SC2089
ADD_QUESTION='{ "pollName" : "poll_curl", "polDeadline":"2020-04-19T13:41:50.573671", "authorId": "poll", "questionDetails":[{ "@type":"TEXT", "questionText":"PollQuestion"}]}'

curl --header "Content-Type: application/json" \
  --request POST \
  --data "$ADD_QUESTION" \
  http://localhost:8080/polls