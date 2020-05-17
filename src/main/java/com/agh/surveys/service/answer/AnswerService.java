package com.agh.surveys.service.answer;

import com.agh.surveys.exception.BadRequestException;
import com.agh.surveys.exception.answer.AnswerNotFoundException;
import com.agh.surveys.model.answer.Answer;
import com.agh.surveys.model.answer.dto.AnswerResponse;
import com.agh.surveys.model.answer.dto.AnswersRequestDto;
import com.agh.surveys.model.answer.type.AnswerDetails;
import com.agh.surveys.model.question.Question;
import com.agh.surveys.model.user.User;
import com.agh.surveys.repository.AnswerRepository;
import com.agh.surveys.repository.QuestionRepository;
import com.agh.surveys.service.user.UserService;
import com.agh.surveys.service.question.QuestionService;
import com.agh.surveys.validation.AnswerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerService implements IAnswerService {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerValidator answerValidator;

    @Override
    public List<Answer> getQuestionAnswers(Integer questionId) {
        return questionService.getQuestion(questionId).getAnswers();
    }


    @Override
    public List<Answer> getAnswerForUserInQuestion(Integer questionID, String userNick){
        Question question = questionService.getQuestion(questionID);
        return question.getAnswers().stream().filter( answer -> answer.getAnswerAuthor().getUserNick().equals(userNick)).collect(Collectors.toList());
    }

    @Override
    public Answer addAnswer(Integer questionID, String authorNick, AnswerDetails answerDetails) {
        User author = userService.getUserByNick(authorNick);
        Question question = questionService.getQuestion(questionID);

        answerValidator.validateAnswer(answerDetails,question);
        answerValidator.validateAuthor(author,question.getQuestionPoll().getPollGroup());

        Answer answer = new Answer(question, author, LocalDateTime.now(), answerDetails);
        question.getAnswers().add(answer);
        answerRepository.save(answer);
        return answer;
    }

    @Override
    public Answer getAnswer(Integer AnswerId) {
        return answerRepository.findById(AnswerId).orElseThrow(AnswerNotFoundException::new);
    }

    @Override
    public void deleteAnswer(Integer answerId){ answerRepository.deleteById(answerId); }

    @Override
    public List<AnswerResponse> answerQuestion(Integer pollId, AnswersRequestDto answersRequestDto) {
        User author = userService.getUserByNick(answersRequestDto.getAnswerAuthor());
        List<AnswerResponse> outp = new ArrayList<>();

        answersRequestDto.getAnswers().forEach(x ->{
            Question question = questionService.getQuestion(x.getQuestionId());
            answerValidator.validateAnswer(x.getDetails(),question);
            answerValidator.validateAuthor(author,question.getQuestionPoll().getPollGroup());
            Answer answer = new Answer(question,author,LocalDateTime.now(),x.getDetails());
            question.getAnswers().add(answer);
            answerRepository.save(answer);
            outp.add(new AnswerResponse(answer));
                }
        );
        return outp;
    }
}
