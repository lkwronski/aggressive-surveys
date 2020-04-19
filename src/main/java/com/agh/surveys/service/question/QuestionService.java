package com.agh.surveys.service.question;


import com.agh.surveys.exception.QuestionNotFoundException;
import com.agh.surveys.model.poll.question.Question;
import com.agh.surveys.model.poll.question.type.QuestionDetails;
import com.agh.surveys.repository.QuestionRepository;
import com.agh.surveys.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService implements  IQuestionService{

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Question addQuestion(QuestionDetails questionDetails) {
        return questionRepository.save(new Question(1L, questionDetails));
    }

    @Override
    public Question findQuestion(Long id) {
       return questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException(id));
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}
