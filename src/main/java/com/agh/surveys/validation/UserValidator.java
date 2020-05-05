package com.agh.surveys.validation;

import com.agh.surveys.exception.BadRequestException;
import com.agh.surveys.model.group.Group;
import com.agh.surveys.model.user.User;
import com.agh.surveys.model.user.dto.UserDto;
import com.agh.surveys.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommonValidator commonValidator;

    private boolean isEmailUsed(String email) {
        return userRepository.findByUserEmail(email).isPresent();
    }

    private boolean isEmailInvalid(String email) {
        return !EmailValidator.getInstance().isValid(email);
    }

    private boolean isUserNickUsed(String nick) {
        return userRepository.findByUserNick(nick).isPresent();
    }

    public void validateNewUserDto(UserDto userDto) {

        String email = userDto.getUserEmail();
        String nick = userDto.getUserNick();
        String firstName = userDto.getUserFirstName();
        String lastName = userDto.getUserLastName();

        if (commonValidator.areBlank(nick, email, firstName, lastName)) {
            throw new BadRequestException("Nick,mail and names cannot be blank");
        }

        if (isEmailUsed(email)) {
            throw new BadRequestException("Email already in use");
        }

        if (isEmailInvalid(email)) {
            throw new BadRequestException("Not valid email");
        }

        if (isUserNickUsed(nick)) {
            throw new BadRequestException("Nick already in use");
        }
    }

    public void validateBeforeDeletion(User user){
        if (!user.getManagedGroups().isEmpty()) {
            throw new BadRequestException("This User is a leader of a group and cannot be deleted!");
        }
    }


}
