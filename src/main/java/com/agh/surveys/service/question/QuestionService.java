package com.agh.surveys.service.question;


import com.agh.surveys.exception.question.QuestionNotFoundException;
import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.question.Question;
import com.agh.surveys.model.question.dto.QuestionCheckBoxDto;
import com.agh.surveys.model.question.dto.QuestionCreateDto;
import com.agh.surveys.model.question.dto.QuestionTimeDto;
import com.agh.surveys.model.question.type.QuestionCheckBox;
import com.agh.surveys.model.question.type.QuestionDetails;
import com.agh.surveys.model.question.type.QuestionText;
import com.agh.surveys.model.question.type.QuestionTime;
import com.agh.surveys.repository.QuestionRepository;
import com.agh.surveys.service.poll.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService implements IQuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    PollService pollService;

    @Override
    public List<Question> getByPollId(Integer pollId) {
        return pollService.getPoll(pollId).getQuestions();
    }

    @Override
    public Question addQuestion(Integer pollId, QuestionCreateDto questionCreateDto) {
        Poll poll = pollService.getPoll(pollId);
        Question question = questionRepository.save(new Question(poll, questionDetailsFromDto(questionCreateDto)));
        poll.getQuestions().add(question);
        pollService.savePoll(poll);
        return question;
    }

    private QuestionDetails questionDetailsFromDto(QuestionCreateDto questionCreateDto) {
        switch(questionCreateDto.getQuestionType()){
            case TEXT:
                return new QuestionText(questionCreateDto.getQuestionText());
            case TIME:
                return new QuestionTime((QuestionTimeDto) questionCreateDto);
            case CHECKBOX:
                return new QuestionCheckBox((QuestionCheckBoxDto) questionCreateDto);
            default:
                throw  new IllegalArgumentException("Cannot create Question details from unknown DTO type");
        }
    }

    @Override
    public List<Question> addAllQuestionDetails(Poll poll, List<QuestionCreateDto> questionDetails) {
        return questionDetails.stream().map( questionDetail -> questionRepository.save(new Question(poll, questionDetailsFromDto(questionDetail)))).collect(Collectors.toList());
    }

    @Override
    public Question getQuestion(Integer questionId) {
        return questionRepository.findById(questionId).orElseThrow(QuestionNotFoundException::new);
    }

    @Override
    public void deleteQuestion(Integer questionId) {
        questionRepository.deleteById(questionId);
    }
}
