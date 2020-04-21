package com.agh.surveys.model.user;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

//table name in postgres cannot be 'user' so added 'entity' suffix
@Data
@Entity(name="user_entity")
public class User {

    @Id
    @Column(name="nick")
    private String nick;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email",unique=true)
    private String email;

    public User(String nick, String firstName, String lastName, String email) {
        this.nick = nick;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User() {
    }
}
