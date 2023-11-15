package ru.skillbox.UsersApplication.service;

import org.springframework.http.HttpStatus;
import ru.skillbox.UsersApplication.dto.UserDto;
import ru.skillbox.UsersApplication.model.User;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();

    UserDto getUser(Integer id);

    UserDto saveUser(UserDto user);

    UserDto updateUser(Integer id, UserDto user);

    String deleteUser(Integer id);
}
