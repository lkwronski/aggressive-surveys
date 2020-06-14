package com.agh.surveys.service.question;

import com.agh.surveys.model.poll.ScheduledPoll;
import com.agh.surveys.model.question.ScheduledQuestion;
import com.agh.surveys.model.question.type.QuestionDetails;
import com.agh.surveys.repository.ScheduledQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduledQuestionService implements IScheduledQuestionService {


    @Autowired
    ScheduledQuestionRepository questionRepository;

    @Override
    public List<ScheduledQuestion> addAllQuestionDetailsToScheduled(ScheduledPoll poll, List<QuestionDetails> questionDetails) {
        return questionDetails.stream().map(qDet -> questionRepository.save(new ScheduledQuestion(poll, qDet))).collect(Collectors.toList());
    }
}
