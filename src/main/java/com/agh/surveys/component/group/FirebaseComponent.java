package com.agh.surveys.component.group;

import com.agh.surveys.model.user.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class FirebaseComponent {

    Firestore db;

    public FirebaseComponent() throws IOException {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        FileInputStream refreshToken = new FileInputStream("refreshToken.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(refreshToken))
                .setDatabaseUrl("https://fcm.googleapis.com/fcm/send")
                .build();

        FirebaseApp.initializeApp(options);
        db = FirestoreClient.getFirestore();
    }

    public void sendNotification(String body, String nick) {
        try {

            Message message = Message.builder()
                    .setNotification(
                            Notification.builder()
                            .setTitle("Survey Time!")
                            .setBody(body)
                            .build()
                    )
                    .setTopic("/topics/Wazzup")
                    .putData("landing_page", "edit-profile")
                    .build();

            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("Successfully sent message: " + response);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendNotificationReminder(User user) {
        sendNotification("You have a survey to fill!", user.getUserNick());
    }

    public void sendNotificationNewPoll(User user) {
        sendNotification("You have new survey to fill!", user.getUserNick());
    }


    private Map<String, String> generateNotification(String body, String nick) {
        Map<String, String> docData = new HashMap<>();

        Map<String, String> notificationData = new HashMap<>();
        notificationData.put("title", "Survey Time!");
        notificationData.put("body", body);
        notificationData.put("click_action", "FCM_PLUGIN_ACTIVITY");

        Map<String, String> data = new HashMap<>();
        data.put("landing_page", "ankieta/:id");

        ObjectMapper mapper = new ObjectMapper();
        String notificationDataJson = null;
        String dataJson = null;
        try {
            notificationDataJson = mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(notificationData);
            dataJson = mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

//        docData.put("notification", notificationDataJson);
//        docData.put("data", dataJson);
        docData.put("to", "/topics/Wazzup");
        docData.put("priority", "high");
        docData.put("restricted_package_name", "");

        return docData;
    }

}
