package com.agh.surveys.component.group;

import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class PollComponent {

    public boolean isUserResponseToPoll(User user, Poll poll) {
        return poll.getQuestions().stream()
                .allMatch(question ->
                        question.getAnswers().stream()
                                .anyMatch(answer -> answer.getAnswerAuthor().equals(user)));
    }

    public boolean isUserNotResponseToPoll(User user, Poll poll) {
        return !isUserResponseToPoll(user,poll);
    }
}
