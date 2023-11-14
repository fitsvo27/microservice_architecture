package ru.skillbox.UsersApplication.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.skillbox.UsersApplication.model.User;
import ru.skillbox.UsersApplication.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUser(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException(String.valueOf(HttpStatus.NOT_FOUND)));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Integer id, User user) {
        if (userRepository.existsById(id)){
            return userRepository.save(user);
        } else throw new RuntimeException(String.valueOf(HttpStatus.NOT_FOUND));
    }

    public String deleteUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException(String.valueOf(HttpStatus.NOT_FOUND)));
        user.setDeleted(true);
        userRepository.save(user);
        return String.format("User with id=%s has been deleted", id);
    }
}
