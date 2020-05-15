package com.agh.surveys.model.message;


import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "message_entity")
public class Message {

    @Id
    @GeneratedValue
    private Integer messageId;

    @Column(name = "content")
    private String messageContent;

    @ManyToOne
    @JoinColumn(name = "userNick")
    private User messageAuthor;

    @Column(name = "deadline")
    private LocalDateTime messageDeadline;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "id")
    private Group messageGroup;

    @ManyToMany
    @JoinTable(
            name = "messages_users_answered",
            joinColumns = @JoinColumn(name = "messageId"),
            inverseJoinColumns = @JoinColumn(name = "userNick")
    )
    private List<User> usersWhoAnswered;

    public boolean isAfterDeadline(){
        return LocalDateTime.now().isAfter(messageDeadline);
    }

    public Message(User author, String context, Group group, LocalDateTime messageDeadline){
        this.messageAuthor=author;
        this.messageContent=context;
        this.messageGroup=group;
        this.messageDeadline=messageDeadline;
        this.usersWhoAnswered = Collections.emptyList();
    }
}
