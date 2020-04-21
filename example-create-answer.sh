#!/bin/sh

# shellcheck disable=SC2089
ADD_ANSWER='{"@type":"TEXT", "answerText":"PollQuestion"}'

curl --header "Content-Type: application/json" \
  --request POST \
  --data "$ADD_ANSWER" \
  http://localhost:8080/user/poll/questions/5/answers