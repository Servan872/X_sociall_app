package org.sosial.controllers;

import lombok.RequiredArgsConstructor;
import org.sosial.dto.request.UserRequest;
import org.sosial.dto.response.UserResponse;
import org.sosial.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserRestController {
    private final UserService userService;

    @GetMapping("/users")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUser();
    }

    @GetMapping("/{username}")
    public UserResponse getOneUserByUsername(@PathVariable String username) {
        return userService.getOneUserByUsername(username);
    }

    @PostMapping("/creat")
    public UserResponse createdUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @PutMapping("/{username}")
    public UserResponse updateUser(@PathVariable String username, @RequestBody UserRequest userRequest) {

        return userService.updateUserByUsername(username, userRequest);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        if (userService.deleteUserByUsername(username)) {
            return ResponseEntity.ok("Delete user successfully");
        } else {
            return ResponseEntity.badRequest().body("user not deleted");
        }

    }

}
