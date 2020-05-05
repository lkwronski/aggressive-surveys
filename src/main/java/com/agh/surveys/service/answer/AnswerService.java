package com.agh.surveys.service.answer;

import com.agh.surveys.exception.BadRequestException;
import com.agh.surveys.exception.answer.AnswerNotFoundException;
import com.agh.surveys.model.answer.Answer;
import com.agh.surveys.model.answer.type.AnswerDetails;
import com.agh.surveys.model.question.Question;
import com.agh.surveys.model.user.User;
import com.agh.surveys.repository.AnswerRepository;
import com.agh.surveys.repository.QuestionRepository;
import com.agh.surveys.service.user.UserService;
import com.agh.surveys.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public List<Answer> getQuestionAnswers(Integer questionId) {
        return questionService.getQuestion(questionId).getAnswers();
    }

    @Override
    public Answer addAnswer(Integer questionID, String authorNick, AnswerDetails answerDetails) {
        User author = userService.getUserByNick(authorNick);
        Question question = questionService.getQuestion(questionID);

        validateAnswerDetails(answerDetails,question);

        validateAnswerAuthor(author,question);

        Answer answer = new Answer(question, author, LocalDateTime.now(), answerDetails);
        question.getAnswers().add(answer);
        answerRepository.save(answer);
        return answer;
    }

    private void validateAnswerDetails(AnswerDetails answerDetails,Question question){
        if(answerDetails.getQuestionType() != question.getQuestionDetails().getQuestionType()){
            throw new BadRequestException("Question and answer type should be the same");
        }


    }

    private void validateAnswerAuthor(User author, Question question){
        if(!userService.isUserInGroup(author,question.getQuestionPoll().getPollGroup())){
            throw new BadRequestException("Answer author must be member of a group!");
        }
    }


    @Override
    public Answer getAnswer(Integer AnswerId) {
        return answerRepository.findById(AnswerId).orElseThrow(AnswerNotFoundException::new);
    }

    @Override
    public void deleteAnswer(Integer answerId){ answerRepository.deleteById(answerId); }

}
