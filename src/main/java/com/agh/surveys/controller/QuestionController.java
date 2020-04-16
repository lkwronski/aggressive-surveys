package com.agh.surveys.controller;

import java.util.List;

import com.agh.surveys.model.poll.question.Question;
import com.agh.surveys.model.poll.question.repository.QuestionNotFoundException;
import com.agh.surveys.model.poll.question.repository.QuestionRepository;
import com.agh.surveys.model.poll.question.type.QuestionDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class QuestionController {

    private final QuestionRepository repository;

    QuestionController(QuestionRepository repository) {
        this.repository = repository;
    }

    // Aggregate root

    @GetMapping("/questions") // TODO brakuje pollID
    List<Question> all() {
        return repository.findAll();
    }

    @PostMapping("/questions")
    Question newEmployee(@RequestBody QuestionDetails questionDetails) {
        return repository.save(new Question(1L, questionDetails)); // TODO brakuje pollID
    }

    // Single item

    @GetMapping("/employees/{id}")
    Question one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException(id));
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
