package org.sosial.dto.response;

import org.sosial.entities.User;

import java.time.LocalDate;

public record UserResponse(String username, String name, String surname, String email, LocalDate birthDay) {


    public static UserResponse convertUserToUserResponse(User savedUser) {
      return  new UserResponse(savedUser.getUsername(),
              savedUser.getName(), savedUser.getSurname(),
                savedUser.getEmail(), savedUser.getBirthday());

    }
}
