package com.agh.surveys.model.group;


import com.agh.surveys.model.message.Message;
import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//I removed Member model as it was same thing as user, then I added user-group many-to-many mapping
@Data
@NoArgsConstructor
@Entity(name = "group_entity")
public class Group {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String groupName;

    @ManyToOne
    @JoinColumn(name = "userNick")
    private User groupLeader;

    @ManyToMany
    @JoinTable(
            name = "user_group",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "userNick")
    )
    private List<User> groupMembers;

    @OneToMany(mappedBy = "pollGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Poll> groupPolls;

    @OneToMany(mappedBy = "messageGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> groupMessages;


    public List<User> getMembersWithAuthor(){
        List<User> outp = new ArrayList<>(groupMembers);
        outp.add(groupLeader);
        return outp;
    }
    public Group(String groupName, User groupLeader, List<User> groupMembers) {
        this.groupName = groupName;
        this.groupLeader = groupLeader;
        this.groupMembers = groupMembers;
        this.groupPolls = Collections.emptyList();
        this.groupMessages = Collections.emptyList();
    }

}