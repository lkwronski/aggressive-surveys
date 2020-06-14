package com.agh.surveys.service.poll;

import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.poll.ScheduledPoll;
import com.agh.surveys.model.question.Question;
import com.agh.surveys.model.question.ScheduledQuestion;
import com.agh.surveys.repository.QuestionRepository;
import com.agh.surveys.service.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PollScheduler {

    @Autowired
    ScheduledPollService scheduledPollService;

    @Autowired
    PollService pollService;

    @Autowired
    GroupService groupService;

    @Autowired
    QuestionRepository questionRepository;

    @Scheduled(cron = "0 0 * ? * *") //cronn expression for "every hour"
    public void createScheduledPolls() {
        System.out.println("XD");
        LocalDateTime now = LocalDateTime.now();
        scheduledPollService.getPollsToCreate()
                .forEach(poll ->createPollFromScheduled(poll,now));

    }

    public Poll createPollFromScheduled(ScheduledPoll scheduledPoll, LocalDateTime creationTime) {
        Poll createdPoll = new Poll();
        Group group = scheduledPoll.getPollGroup();
        createdPoll.setPollGroup(group);
        createdPoll.setAuthor(scheduledPoll.getAuthor());
        createdPoll.setPolDeadline(scheduledPoll.getPollScheduleTime().plusNanos(scheduledPoll.getScheduledDeadline().toNanos()));
        createdPoll.setPollCreationTime(creationTime);
        createdPoll.setPollName(scheduledPoll.getPollName());
        List<Question> questions = new LinkedList<>();
        createdPoll.setQuestions(questions);
        pollService.savePoll(createdPoll);
        questions.addAll(createQuestionsFromScheduled(scheduledPoll.getQuestions(), createdPoll));
        groupService.saveGroup(group);

        return createdPoll;
    }

    public List<Question> createQuestionsFromScheduled(List<ScheduledQuestion> scheduledQuestions, Poll poll) {
        return scheduledQuestions.stream()
                .map(question -> createQuestionFromScheduled(question, poll))
                .collect(Collectors.toList());
    }

    private Question createQuestionFromScheduled(ScheduledQuestion scheduledQuestion, Poll poll) {
        return questionRepository.save(new Question(poll, scheduledQuestion.getQuestionDetails()));
    }
}
