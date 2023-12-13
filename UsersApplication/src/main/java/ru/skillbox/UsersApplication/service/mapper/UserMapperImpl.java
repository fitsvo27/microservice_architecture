package ru.skillbox.UsersApplication.service.mapper;

import org.springframework.stereotype.Service;
import ru.skillbox.UsersApplication.dto.UserDto;
import ru.skillbox.UsersApplication.model.User;

@Service
public class UserMapperImpl implements UserMapper{
    public UserDto userToUserDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .lastname(user.getLastname())
                .firstname(user.getFirstname())
                .middlename(user.getMiddlename())
                .dateOfBirth(user.getDateOfBirth())
                .gender(user.getGender())
                .avatarLink(user.getAvatarLink())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .deleted(user.getDeleted())
                .build();
    }
    public User userDtoToUser(UserDto userDto){
        return new User(userDto.getId(), userDto.getLogin(), userDto.getLastname(), userDto.getFirstname(),
                userDto.getMiddlename(), userDto.getGender(), userDto.getDateOfBirth(), userDto.getAvatarLink(),
                userDto.getEmail(), userDto.getPhoneNumber(), userDto.getDeleted());
    }
}
