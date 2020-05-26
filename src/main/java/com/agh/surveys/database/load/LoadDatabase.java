package com.agh.surveys.database.load;

import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.group.dto.GroupCreateDto;
import com.agh.surveys.model.question.type.QuestionDetails;
import com.agh.surveys.model.user.User;
import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.question.Question;
import com.agh.surveys.model.user.dto.UserDto;
import com.agh.surveys.repository.GroupRepository;
import com.agh.surveys.repository.PollRepository;
import com.agh.surveys.repository.QuestionRepository;
import com.agh.surveys.model.question.type.QuestionText;
import com.agh.surveys.repository.UserRepository;
import com.agh.surveys.service.user.UserService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initUsersAndGroups(PollRepository pollRepository, UserRepository userRepository, QuestionRepository questionRepository, GroupRepository groupRepository) {
        //User user =  new User("poll", "test", "test", "test");
        User blecharczyk =  new User(new UserDto("blecharczyk", "Krzysztof", "Blecharczyk", "blecharczyk@mail.pl"));
        User pokorski =  new User(new UserDto("pokorski", "Krzysztof", "Pokorski", "pokorski@mail.pl"));
        User kowalski =  new User(new UserDto("kowalski", "Łukasz", "Kowalski", "kowalski@mail.pl"));
        User stando =  new User(new UserDto("stando", "Aleksander", "Stańdo", "stando@mail.pl"));
        User wronski =  new User(new UserDto("wronski", "Łukasz", "Wroński", "wronski@mail.pl"));
        User romanczyk =  new User(new UserDto("romanczyk", "Hubert", "Romańczyk", "romanczyk@mail.pl"));
        User idzik =  new User(new UserDto("idzik", "Michał", "Idzik", "idzik@mail.pl"));

        //backend
        List<User> backendList = new ArrayList();
        backendList.add(blecharczyk);
        backendList.add(kowalski);
        backendList.add(romanczyk);
        backendList.add(wronski);
        Group backend = new Group("backend", kowalski, backendList);
        //frontend
        List<User> frontendList = new ArrayList();
        frontendList.add(stando);
        frontendList.add(pokorski);
        frontendList.add(romanczyk);
        Group frontend = new Group("frontend", pokorski, frontendList);
        //client
        List<User> clientList = new ArrayList();
        clientList.add(idzik);
        Group client = new Group("client", idzik, clientList);

        return args -> {
            log.info("Preloading " + userRepository.save(blecharczyk));
            log.info("Preloading " + userRepository.save(pokorski));
            log.info("Preloading " + userRepository.save(kowalski));
            log.info("Preloading " + userRepository.save(stando));
            log.info("Preloading " + userRepository.save(wronski));
            log.info("Preloading " + userRepository.save(romanczyk));
            log.info("Preloading " + userRepository.save(idzik));
            log.info("Preloading " + groupRepository.save(backend));
            log.info("Preloading " + groupRepository.save(frontend));
            log.info("Preloading " + groupRepository.save(client));
        };

    }





}