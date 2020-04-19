package com.agh.surveys.service.poll;

import com.agh.surveys.exception.PollNotFoundException;
import com.agh.surveys.model.User;
import com.agh.surveys.model.poll.question.Poll;
import com.agh.surveys.model.poll.question.Question;
import com.agh.surveys.model.poll.question.type.QuestionDetails;
import com.agh.surveys.repository.PollRepository;
import com.agh.surveys.service.UserService;
import com.agh.surveys.service.poll.dto.PollPost;
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
    public Poll findPoll(Long id) {
        return pollRepository.findById(id)
                .orElseThrow(() -> new PollNotFoundException(id));
    }


    @Override
    public Poll addPoll(PollPost pollPost) {
        User author = userService.getUserByNick(pollPost.getAuthorId());
        List<Question> questions = questionService.addAllQuestionDetails(pollPost.getQuestionDetails());
        Poll poll = new Poll(pollPost.getPollName(), LocalDateTime.now(), pollPost.getPolDeadline(), author, questions);
        return pollRepository.save(poll);
    }

    @Override
    public void deletePoll(Long id) {
        pollRepository.deleteById(id);
    }
}
