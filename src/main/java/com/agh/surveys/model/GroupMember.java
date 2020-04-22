package com.agh.surveys.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "group_member_entity")
public class GroupMember {

    @Id
    @Column(name = "id")
    private Integer id;

    @Id
    @Column(name = "nick")
    private String nick;

}
