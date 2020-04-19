package com.agh.surveys.controller;

import com.agh.surveys.model.poll.question.Question;
import com.agh.surveys.model.poll.question.type.QuestionDetails;
import com.agh.surveys.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
class QuestionController {

    @Autowired
    QuestionService questionService;

    // Aggregate root

    @GetMapping // TODO brakuje pollID
    List<Question> all() {
        return questionService.findAll();
    }

    @PostMapping
    Question addQuestion(@RequestBody QuestionDetails questionDetails) {
        return questionService.addQuestion(questionDetails); // TODO brakuje pollID
    }

    @GetMapping("/{id}")
    Question findQuestion(@PathVariable Long id) {
        return questionService.findQuestion(id);
    }

    @DeleteMapping("/{id}")
    void deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
    }
}
