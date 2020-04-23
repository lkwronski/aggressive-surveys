package com.agh.surveys.service.poll;

import com.agh.surveys.exception.poll.PollNotFoundException;
import com.agh.surveys.model.user.User;
import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.question.Question;
import com.agh.surveys.repository.PollRepository;
import com.agh.surveys.service.user.UserService;
import com.agh.surveys.model.poll.dto.PollCreateDto;
import com.agh.surveys.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PollService implements IPollService{

    @Autowired
    PollRepository pollRepository;

    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;

    @Override
    public List<Poll> findAll() {
        return pollRepository.findAll();
    }

    @Override
    public Poll getPoll(Long id) {
        return pollRepository.findById(id)
                .orElseThrow(() -> new PollNotFoundException());
    }


    @Override
    public Poll addPoll(PollCreateDto pollCreateDto) {
        User author = userService.getUserByNick(pollCreateDto.getAuthorId());
        List<Question> questions = questionService.addAllQuestionDetails(pollCreateDto.getQuestionDetails());
        Poll poll = new Poll(pollCreateDto.getPollName(), LocalDateTime.now(), pollCreateDto.getPolDeadline(), author, questions);
        return pollRepository.save(poll);
    }

    @Override
    public void deletePoll(Long id) {
        pollRepository.deleteById(id);
    }

    @Override
    public void savePoll(Poll poll) {
        pollRepository.save(poll);
    }
}
