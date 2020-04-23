package com.agh.surveys.controller;

import com.agh.surveys.model.question.Question;
import com.agh.surveys.model.question.type.QuestionDetails;
import com.agh.surveys.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/{questionId}")
    Question findQuestion(@PathVariable Long questionId) {
        return questionService.getQuestion(questionId);
    }

    @DeleteMapping("/{questionId}")
    void deleteQuestion(@PathVariable Long questionId) {
        questionService.deleteQuestion(questionId);
    }
}
