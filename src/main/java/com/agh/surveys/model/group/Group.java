package com.agh.surveys.model.group;


import com.agh.surveys.model.poll.Poll;
import com.agh.surveys.model.user.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

//I removed Member model as it was same thing as user, then I added user-group many-to-many mapping
@Getter
@Setter
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_group",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "userNick")
    )
    private List<User> groupMembers;

    @OneToMany(mappedBy = "pollGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Poll> groupPolls;

    public Group(String groupName, User groupLeader, List<User> groupMembers) {
        this.groupName = groupName;
        this.groupLeader = groupLeader;
        this.groupMembers = groupMembers;
        this.groupPolls = Collections.emptyList();
    }

}
