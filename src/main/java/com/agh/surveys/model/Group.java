package com.agh.surveys.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "group_entity")
public class Group {

    @Id
    @Column(name = "ID")
    private Integer ID;

    @Column(name = "group_name")
    private String groupName;



}
