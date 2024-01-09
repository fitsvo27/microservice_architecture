package ru.skillbox.UsersApplication.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.UsersApplication.dto.UserDto;
import ru.skillbox.UsersApplication.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Получить список всех пользователей")
    @GetMapping
    List<UserDto> getUsers(){
        return userService.getUsers();
    }

    @Operation(summary = "Получить список всех пользователей")
    @GetMapping(path = "/{id}")
    UserDto getUser(@PathVariable long id){
        return userService.getUser(id);
    }

    @Operation(summary = "Сохранить нового пользователя")
    @PostMapping
    UserDto saveUser(@RequestBody UserDto userDto){
        return userService.saveUser(userDto);
    }

    @Operation(summary = "Редактировать пользователя по идентификатору")
    @PutMapping(path = "/{id}")
    UserDto updateUser(@PathVariable long id, @RequestBody UserDto userDto){
        return userService.updateUser( id, userDto);
    }

    @Operation(summary = "Удалить пользователя по идентификатору")
    @DeleteMapping(path = "/{id}")
    String deleteUser(@PathVariable long id){return userService.deleteUser(id);}
}
