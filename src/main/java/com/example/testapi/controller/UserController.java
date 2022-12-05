package com.example.testapi.controller;

import com.example.testapi.dto.UserDto;
import com.example.testapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = "api/v1/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        var user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "api/v1/users/")
    public UserDto createUser(@RequestParam("login") String login,
                                                @RequestParam("password") String password) {
        return userService.addUser(login, password);
    }

    @GetMapping(value = "api/v1/login/")
    public UserDto login(@RequestParam("login") String login,
                              @RequestParam("password") String password) {
        return userService.login(login, password);
    }

    @GetMapping(value = "api/v1/users/")
    public List<UserDto> getUsers() {
        return userService.findAllUsers();
    }
}
