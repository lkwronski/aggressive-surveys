package com.agh.surveys.component.group;

import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class GroupComponent {

    public boolean userResponseToPoll(User user, Poll poll) {
        return poll.getQuestions().stream()
                .allMatch(question ->
                        question.getAnswers().stream()
                                .anyMatch(answer -> answer.getAnswerAuthor().equals(user)));
    }

    public boolean userNotResponseToPoll(User user, Poll poll) {
        return !userResponseToPoll(user,poll);
    }
}
