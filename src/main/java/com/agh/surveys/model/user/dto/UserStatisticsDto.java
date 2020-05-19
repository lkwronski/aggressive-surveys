package com.agh.surveys.model.user.dto;

import com.agh.surveys.model.answer.Answer;
import com.agh.surveys.model.answer.dto.AnswerResponse;
import com.agh.surveys.model.question.Question;
import com.agh.surveys.model.question.dto.QuestionResponse;
import com.agh.surveys.model.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserStatisticsDto {

   private UserDto user;
   private List<AnswerResponse> answers;
   private QuestionResponse questionResponse;

    public UserStatisticsDto(User user) {
        this.user = new UserDto(user);
    }

    public UserStatisticsDto(User user, Question question, List<Answer> userAnswers) {
       this.user = new UserDto(user);
       this.questionResponse = new QuestionResponse(question);
       this.answers = userAnswers.stream().map(AnswerResponse::new).collect(Collectors.toList());
    }
}
