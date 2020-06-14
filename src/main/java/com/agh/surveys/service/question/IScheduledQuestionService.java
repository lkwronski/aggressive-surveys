package com.agh.surveys.service.question;

import com.agh.surveys.model.poll.ScheduledPoll;
import com.agh.surveys.model.question.ScheduledQuestion;
import com.agh.surveys.model.question.type.QuestionDetails;

import java.util.List;

public interface IScheduledQuestionService {
    List<ScheduledQuestion> addAllQuestionDetailsToScheduled(ScheduledPoll poll, List<QuestionDetails> questionDetails);
}
