package com.agh.surveys.model.user;

import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.message.Message;
import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.user.dto.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//table name in postgres cannot be 'user' so added 'entity' suffix  (LK)
@Data
@NoArgsConstructor
@Entity(name = "user_entity")
public class User {

    @Id
    @Column(name = "nick")
    private String userNick;

    @Column(name = "first_name")
    private String userFirstName;

    @Column(name = "last_name")
    private String userLastName;

    @Column(name = "email", unique = true)
    private String userEmail;

    @ToString.Exclude
    @ManyToMany(mappedBy = "groupMembers", cascade = CascadeType.PERSIST)
    private List<Group> userGroups;

    @ToString.Exclude
    @ManyToMany(mappedBy = "usersWhoAnswered", cascade = CascadeType.PERSIST)
    private List<Message> answeredMessages;

    @ToString.Exclude
    @OneToMany(mappedBy = "groupLeader", cascade = CascadeType.PERSIST)
    private List<Group> managedGroups;

    @ToString.Exclude
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Poll> createdPolls;

    public User(UserDto dto) {
        this.userNick = dto.getUserNick();
        this.userFirstName = dto.getUserFirstName();
        this.userLastName = dto.getUserLastName();
        this.userEmail = dto.getUserEmail();
    }

    public List<Group> getAllGroups() {
        return Stream.concat(managedGroups.stream(), userGroups.stream())
                .collect(Collectors.toList());
    }

}
