#!/bin/sh

# shellcheck disable=SC2089
ADD_QUESTION='{"@type":"TEXT", "questionText":"PollQuestion"}'

curl --header "Content-Type: application/json" \
  --request POST \
  --data "$ADD_QUESTION" \
  http://localhost:8080/polls/7/questions