package com.agh.surveys.service.question;


import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.question.Question;
import com.agh.surveys.model.question.type.QuestionDetails;
import com.agh.surveys.repository.QuestionRepository;
import com.agh.surveys.service.poll.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService implements  IQuestionService{

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    PollService pollService;

    @Override
    public List<Question> findAll(Long poolId) {
        return pollService.getPoll(poolId).getQuestions();
    }

    @Override
    public Question addQuestion(Long poolId, QuestionDetails questionDetails) {
        Question question = questionRepository.save(new Question(questionDetails));
        Poll poll = pollService.getPoll(poolId);
        poll.getQuestions().add(question);
        pollService.savePoll(poll);
        return question;
    }

    @Override
    public List<Question> addAllQuestionDetails(List<QuestionDetails> questionDetails) {
        return questionDetails.stream().map( questionDetail -> questionRepository.save(new Question(questionDetail))).collect(Collectors.toList());
    }

    @Override
    public Question getQuestion(Long poolId, Long questionId) {
        return questionRepository.getOne(questionId);
    }

    @Override
    public void deleteQuestion(Long poolId, Long questionId) {
       questionRepository.deleteById(questionId);
    }
}
