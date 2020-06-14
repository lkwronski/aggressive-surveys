package com.agh.surveys.validation;

import com.agh.surveys.exception.BadRequestException;
import com.agh.surveys.model.answer.dto.AnswerCreateDto;
import com.agh.surveys.model.answer.type.AnswerDetails;
import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.question.Question;
import com.agh.surveys.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnswerValidator {

    @Autowired
    GroupValidator groupValidator;

    public void validateAnswer(AnswerCreateDto answerCreateDto, Question question){
        if(answerCreateDto.getQuestionType() != question.getQuestionDetails().getQuestionType()){
            throw new BadRequestException("Question and answer type should be the same");
        }
    }

    public void validateAuthor(User author, Group group){
        if(!groupValidator.isUserMemberOrLeader(author,group)){
            throw new BadRequestException("Author of an answer must be member of a group");
        }
    }

}
