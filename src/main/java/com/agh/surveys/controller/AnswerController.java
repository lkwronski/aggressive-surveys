package com.agh.surveys.controller;

import com.agh.surveys.model.answer.dto.AnswerResponse;
import com.agh.surveys.model.answer.type.AnswerDetails;
import com.agh.surveys.service.answer.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = {"http://localhost:8100","http://localhost:8080"})
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @GetMapping("questions/{questionId}/answers")
    List<AnswerResponse> getQuestionAnswers(@PathVariable Integer questionId) {
        return answerService.getQuestionAnswers(questionId).stream().map(AnswerResponse::new).collect(Collectors.toList());
    }

    @PostMapping("user/{userId}/questions/{questionId}/answers")
    AnswerResponse addAnswer(@PathVariable Integer questionId, @PathVariable String userId, @RequestBody AnswerDetails answerDetails) {
        return new AnswerResponse(answerService.addAnswer(questionId, userId, answerDetails));
    }

    @GetMapping("/answer/{answerId}")
    AnswerResponse findAnswer(@PathVariable Integer answerId) {
        return new AnswerResponse(answerService.getAnswer(answerId));
    }

    @DeleteMapping("/answer/{answerId}")
    void deleteAnswer(@PathVariable Integer answerId) {
        answerService.deleteAnswer(answerId);
    }



}
