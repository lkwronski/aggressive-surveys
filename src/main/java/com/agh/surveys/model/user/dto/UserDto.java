package com.agh.surveys.model.user.dto;

import com.agh.surveys.model.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private String userNick;
    private String userFirstName;
    private String userLastName;
    private String userEmail;

    public UserDto(String userNick, String userFirstName, String userLastName, String userEmail){
        this.userEmail= userEmail;
        this.userFirstName= userFirstName;
        this.userLastName= userLastName;
        this.userNick= userNick;
    }
}
