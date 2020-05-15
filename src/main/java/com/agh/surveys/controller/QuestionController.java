package com.agh.surveys.controller;

import com.agh.surveys.model.question.dto.QuestionResponse;
import com.agh.surveys.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("questions")
@CrossOrigin(origins = {"http://localhost:8100", "http://localhost:8080"})
class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/{questionId}")
    QuestionResponse findQuestion(@PathVariable Integer questionId) {
        return new QuestionResponse(questionService.getQuestion(questionId));
    }

    @DeleteMapping("/{questionId}")
    void deleteQuestion(@PathVariable Integer questionId) {
        questionService.deleteQuestion(questionId);
    }
}
