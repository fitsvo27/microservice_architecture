package ru.skillbox.UsersApplication.service;

import org.springframework.http.HttpStatus;
import ru.skillbox.UsersApplication.dto.UserDto;
import ru.skillbox.UsersApplication.model.User;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();

    UserDto getUser(long id);

    UserDto saveUser(UserDto user);

    UserDto updateUser(long id, UserDto user);

    String deleteUser(long id);
}
