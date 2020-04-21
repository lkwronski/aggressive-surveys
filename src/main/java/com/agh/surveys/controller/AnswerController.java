package com.agh.surveys.controller;

import com.agh.surveys.service.answer.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("questions")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @DeleteMapping(/)

}
