package com.agh.surveys.controller;

import com.agh.surveys.model.answer.Answer;
import com.agh.surveys.model.answer.type.AnswerDetails;
import com.agh.surveys.service.answer.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @GetMapping("questions/{questionId}/answers")
    List<Answer> all(@PathVariable Long questionId) {
        return answerService.findAll(questionId);
    }

    @PostMapping("user/{userId}/questions/{questionId}/answers")
    Answer addAnswer(@PathVariable Long questionId, @PathVariable String userId, @RequestBody AnswerDetails answerDetails) {
        return answerService.addAnswer(questionId, userId, answerDetails);
    }

    @GetMapping("/answer/{answerId}")
    Answer findAnswer(@PathVariable Long answerId) {
        return answerService.getAnswer(answerId);
    }

    @DeleteMapping("/answer/{answerId}")
    void deleteAnswer(@PathVariable Long answerId) {
        answerService.deleteAnswer(answerId);
    }

}
