package com.agh.surveys.service.answer;

import com.agh.surveys.model.answer.Answer;
import com.agh.surveys.model.answer.type.AnswerDetails;
import com.agh.surveys.repository.AnswerRepository;
import com.agh.surveys.repository.QuestionRepository;
import com.agh.surveys.service.poll.PollService;
import com.agh.surveys.service.question.IQuestionService;
import com.agh.surveys.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService implements IAnswerService {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionRepository questionRepository;


    @Override
    public List<Answer> findAll(Long questionId) {
        return null;
    }

    @Override
    public Answer addAnswer(Long questionID, Long userID, AnswerDetails answerDetails) {
        return null;
    }

    @Override
    public Answer getAnswer(Long AnswerId) {
        return answerRepository.getOne(AnswerId);
    }

    @Override
    public void deleteAnswer(Long answerId){ answerRepository.deleteById(answerId); }


}
