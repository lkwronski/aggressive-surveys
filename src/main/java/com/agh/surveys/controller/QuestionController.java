package com.agh.surveys.controller;

import java.util.List;

import com.agh.surveys.model.poll.question.Question;
import com.agh.surveys.model.poll.question.repository.QuestionNotFoundException;
import com.agh.surveys.model.poll.question.repository.QuestionRepository;
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

    @GetMapping("/employees")
    List<Question> all() {
        return repository.findAll();
    }

    @PostMapping("/employees")
    Question newEmployee(@RequestBody Question newEmployee) {
        return repository.save(newEmployee);
    }

    // Single item

    @GetMapping("/employees/{id}")
    Question one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException(id));
    }

//    @PutMapping("/employees/{id}")
//    Question replaceEmployee(@RequestBody Question newQuestion, @PathVariable Long id) {
//
//        return repository.findById(id)
//                .map(employee -> {
//                    employee.setName(newQuestion.getName());
//                    employee.setRole(newQuestion.getRole());
//                    return repository.save(employee);
//                })
//                .orElseGet(() -> {
//                    newQuestion.setId(id);
//                    return repository.save(newQuestion);
//                });
//    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
