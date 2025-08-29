package org.sosial.dto.request;



import org.sosial.entities.User;

import java.time.LocalDate;

public record UserRequest(String username,
                          String name,
                          String surname,
                          String email,
                          String password,
                          LocalDate birthdate) {

public static User convertUserRequestToUser(UserRequest userRequest) {
    User user = new User();
    user.setUsername(userRequest.username);
    user.setName(userRequest.name);
    user.setSurname(userRequest.surname);
    user.setEmail(userRequest.email);
    user.setPassword(userRequest.password);
    user.setBirthday(userRequest.birthdate);
    return user;

}
}
