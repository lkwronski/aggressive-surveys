package com.agh.surveys.service.poll;

import com.agh.surveys.component.group.GroupComponent;
import com.agh.surveys.exception.poll.PollNotFoundException;
import com.agh.surveys.model.answer.Answer;
import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.poll.dto.PollStatisticsDto;
import com.agh.surveys.model.question.Question;
import com.agh.surveys.model.user.User;
import com.agh.surveys.model.user.dto.UserStatisticsDto;
import com.agh.surveys.repository.PollRepository;
import com.agh.surveys.service.answer.AnswerService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PollService implements IPollService {

    @Autowired
    PollRepository pollRepository;

    @Autowired
    AnswerService answerService;

    @Autowired
    GroupComponent groupComponent;

    @Override
    public List<Poll> findAll() {
        return pollRepository.findAll();
    }

    @Override
    public Poll getPoll(Integer id) {
        return pollRepository.findById(id)
                .orElseThrow(PollNotFoundException::new);
    }

    @Override
    public void deletePoll(Integer id) {
        pollRepository.deleteById(id);
    }

    @Override
    public Poll savePoll(Poll poll) {
        return pollRepository.save(poll);
    }

    @Override
    public List<User> getRespondedUser(Integer pollId) {
        Poll poll = getPoll(pollId);
        List<User> groupUsers = poll.getPollGroup().getGroupMembers();
        List<User> respondedUsers = groupUsers.stream().filter(user -> groupComponent.userResponseToPoll(user, poll)).collect(Collectors.toList());
        return respondedUsers;
    }

    @Override
    public List<User> getNotRespondedUser(Integer pollId) {
        Poll poll = getPoll(pollId);
        List<User> groupUsers = poll.getPollGroup().getGroupMembers();
        List<User> respondedUsers = groupUsers.stream().filter(user -> groupComponent.userNotResponseToPoll(user, poll)).collect(Collectors.toList());
        return respondedUsers;
    }

    @Override
    public PollStatisticsDto getStatistics(Integer pollId) {
        Poll poll = getPoll(pollId);
        PollStatisticsDto pollStatisticsDto = new PollStatisticsDto();
        List<UserStatisticsDto> notRespondedUser = getNotRespondedUser(pollId).stream().map(UserStatisticsDto::new).collect(Collectors.toList());
        pollStatisticsDto.setNotRespondedUser(notRespondedUser);

        List<UserStatisticsDto> respondedUser = getRespondedUser(pollId).stream()
                .flatMap(user -> {
                            List<Question> pollQuestions = poll.getQuestions();
                            List<UserStatisticsDto> userAnswerQuestions = pollQuestions.stream().map(question -> {

                                List<Answer> userAnswers = answerService.getAnswerForUserInQuestion(question.getQuestionId(), user.getUserNick());
                                return new UserStatisticsDto(user, question, userAnswers);
                            }).collect(Collectors.toList());

                            return userAnswerQuestions.stream();
                        }
                ).collect(Collectors.toList());

        pollStatisticsDto.setRespondedUser(respondedUser);
        return pollStatisticsDto;
    }
}
