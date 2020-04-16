package com.agh.surveys.database.load;

import com.agh.surveys.model.poll.question.Question;
import com.agh.surveys.model.poll.question.repository.QuestionRepository;
import com.agh.surveys.model.poll.question.type.QuestionText;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(QuestionRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Question(1L, new QuestionText("test"))));
            log.info("Preloading " + repository.save(new Question(2L, new QuestionText("test2"))));
        };
    }
}