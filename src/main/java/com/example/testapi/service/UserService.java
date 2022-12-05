package com.example.testapi.service;

import com.example.testapi.dto.UserDto;
import com.example.testapi.entity.User;
import com.example.testapi.reposity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto getUser(Long userId) {
        var user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException(String.format("User with id=%s not found", userId)));
        return map(user);

    }

    public UserDto addUser(String login, String password){
        final User user = new User(login, password);
        UserDto userDto = map(user);
        if (findAllUsers().contains(userDto)){
            userDto.setResponse(false);
            return userDto;
        }
        else {
            return map(userRepository.save(user));
        }
    }

    public List<UserDto> findAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        List<UserDto> usersDto = new ArrayList<UserDto>();
        for (User user : users) {
            usersDto.add(map(user));
        }
        return usersDto;
    }

    public UserDto login(String login, String password){
        List<User> users = (List<User>) userRepository.findAll();
        User user = new User(login, password);
        UserDto userDto = map(user);
        boolean contains = false;
        for (User usr : users){
            if (Objects.equals(usr.getLogin(), user.getLogin()) &&
                    Objects.equals(usr.getPassword(), user.getPassword())) {
                contains = true;
                break;
            }
        }
        userDto.setResponse(contains);
        return userDto;
    }

    private UserDto map(User entity) {
        return UserDto.builder()
                .login(entity.getLogin())
                .response(true)
                .build();
    }
}
