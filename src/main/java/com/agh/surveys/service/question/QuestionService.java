package com.agh.surveys.service.question;


import com.agh.surveys.exception.question.QuestionNotFoundException;
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
    public List<Question> getByPollId(Integer poolId) {
        return pollService.getPoll(poolId).getQuestions();
    }

    @Override
    public Question addQuestion(Integer poolId, QuestionDetails questionDetails) {
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
    public Question getQuestion(Integer questionId) {
        return questionRepository.findById(questionId).orElseThrow(QuestionNotFoundException::new);
    }

    @Override
    public void deleteQuestion(Integer questionId) {
        Question deleteQuestion = getQuestion(questionId); // TODO naprawić usuwanie pytania
        List<Poll> polls = pollService.findAll().stream().filter( poll -> poll.getQuestions().stream().anyMatch( question -> question.getQuestionId().equals(questionId))).collect(Collectors.toList());
        polls.forEach(poll -> {
                poll.getQuestions().remove(deleteQuestion);
                pollService.savePoll(poll);
        });
       questionRepository.deleteById(questionId);
    }
}
