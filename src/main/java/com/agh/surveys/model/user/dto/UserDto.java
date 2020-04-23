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

    public UserDto(User user){
        this.userEmail= user.getUserEmail();
        this.userFirstName= user.getUserFirstName();
        this.userLastName= user.getUserLastName();
        this.userNick= user.getUserNick();
    }
}
