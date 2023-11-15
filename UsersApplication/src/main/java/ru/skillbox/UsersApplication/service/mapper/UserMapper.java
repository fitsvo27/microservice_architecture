package ru.skillbox.UsersApplication.service.mapper;

import ru.skillbox.UsersApplication.dto.UserDto;
import ru.skillbox.UsersApplication.model.User;

public interface UserMapper {
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
}
