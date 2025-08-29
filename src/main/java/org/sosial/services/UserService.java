package org.sosial.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.sosial.dto.request.UserRequest;
import org.sosial.dto.response.UserResponse;
import org.sosial.entities.User;
import org.sosial.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.yaml.snakeyaml.nodes.Tag.STR;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        User user = UserRequest.convertUserRequestToUser(userRequest);
        User savedUser = userRepository.save(user);
        return UserResponse.convertUserToUserResponse(savedUser);
    }

    @Transactional
    public List<UserResponse> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(UserResponse::convertUserToUserResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserResponse getOneUserByUsername(String username) {
        User foundUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException(username + "is not found"));
        return UserResponse.convertUserToUserResponse(foundUser);
    }

    @Transactional
    public UserResponse updateUserByUsername(String username, UserRequest userRequest) {

        User foundUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException(username + "is not found"));

        User requestUser = UserRequest.convertUserRequestToUser(userRequest);
        User user = updateUser(foundUser, requestUser);
        User savedUser = userRepository.save(user);
        return UserResponse.convertUserToUserResponse(savedUser);

    }

    @Transactional(Transactional.TxType.SUPPORTS)
    protected User updateUser(User foundUser, User requestUser) {
        Optional.ofNullable(requestUser.getName()).ifPresent(foundUser::setName);
        Optional.ofNullable(requestUser.getSurname()).ifPresent(foundUser::setSurname);
        Optional.ofNullable(requestUser.getBirthday()).ifPresent(foundUser::setBirthday);
        return foundUser;
    }


    public Boolean deleteUserByUsername(String username) {
        try {
            User foundUser = userRepository.findByUsername(username)
                    .orElseThrow(() -> new IllegalArgumentException(username + "is not found"));
            userRepository.delete(foundUser);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
