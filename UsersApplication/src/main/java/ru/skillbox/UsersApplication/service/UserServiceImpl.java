package ru.skillbox.UsersApplication.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.skillbox.UsersApplication.dto.UserDto;
import ru.skillbox.UsersApplication.model.User;
import ru.skillbox.UsersApplication.repository.UserRepository;
import ru.skillbox.UsersApplication.service.mapper.UserMapper;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDto> getUsers(){
        return userRepository.findAll().stream().map(userMapper::userToUserDto).toList();
    }

    public UserDto getUser(long id) {
        return userMapper.userToUserDto(userRepository.findById(id).orElseThrow(() -> new RuntimeException(String.valueOf(HttpStatus.NOT_FOUND))));
    }

    public UserDto saveUser(UserDto userDto) {
        return userMapper.userToUserDto(userRepository.save(userMapper.userDtoToUser(userDto)));
    }

    public UserDto updateUser(long id, UserDto userDto) {
        if (userRepository.existsById(id)){
            return userMapper.userToUserDto(userRepository.save(userMapper.userDtoToUser(userDto)));
        } else throw new RuntimeException(String.valueOf(HttpStatus.NOT_FOUND));
    }

    public String deleteUser(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException(String.valueOf(HttpStatus.NOT_FOUND)));
        user.setDeleted(true);
        userRepository.save(user);
        return String.format("User with id=%s has been deleted", id);
    }
}
