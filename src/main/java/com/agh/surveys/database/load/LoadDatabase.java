package com.agh.surveys.database.load;

import com.agh.surveys.model.user.User;
import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.question.Question;
import com.agh.surveys.repository.PollRepository;
import com.agh.surveys.repository.QuestionRepository;
import com.agh.surveys.model.question.type.QuestionText;
import com.agh.surveys.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Configuration
@Slf4j
class LoadDatabase {

    /*
    @Bean
    CommandLineRunner initQuestion(QuestionRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Question(new QuestionText("test"))));
            log.info("Preloading " + repository.save(new Question(new QuestionText("test2"))));
        };
    }

    @Bean
    CommandLineRunner initPoll(PollRepository pollRepository, UserRepository userRepository, QuestionRepository questionRepository) {
        User user =  new User("poll", "test", "test", "test");
        Question question = new Question(new QuestionText("PollQuestion"));
        List<Question> pollQuestions =  new LinkedList();
        pollQuestions.add(question);
        return args -> {
            log.info("Preloading " + userRepository.save(user));
            log.info("Preloading " + questionRepository.save(question));
            log.info("Preloading " + pollRepository.save(new Poll("poll_init", LocalDateTime.now(), LocalDateTime.now(),user, pollQuestions)));
        };
    }

     */
}