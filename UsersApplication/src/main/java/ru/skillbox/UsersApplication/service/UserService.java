package ru.skillbox.UsersApplication.service;

import org.springframework.http.HttpStatus;
import ru.skillbox.UsersApplication.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User getUser(Integer id);

    User saveUser(User user);

    User updateUser(Integer id, User user);

    String deleteUser(Integer id);
}
