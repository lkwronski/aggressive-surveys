package com.agh.surveys.component.group;

import com.agh.surveys.controller.UsersController;
import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.poll.dto.PollResponseDto;
import com.agh.surveys.model.user.User;
import com.agh.surveys.service.user.UserService;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Component
public class NotificationComponent {

    @Autowired
    UserService userService;

    @Autowired
    UsersController usersController;

    @Autowired
    FirebaseComponent firebaseComponent;

    @Scheduled(cron = "0 * * * * *")
    public void sendReminderToFillPoll(){
        List<User> users = userService.getAll();
        users.stream().forEach( user -> {
            List<PollResponseDto> unfilledPolls = usersController.getUnfilledPolls(user.getUserNick());
            if(!unfilledPolls.isEmpty()){
                firebaseComponent.sendNotificationReminder(user);
                System.out.println(user.getUserNick());;
            }
        });
    }

}
