package ru.skillbox.UsersApplication.controller;

import org.springframework.web.bind.annotation.*;
import ru.skillbox.UsersApplication.dto.UserDto;
import ru.skillbox.UsersApplication.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    List<UserDto> getUsers(){
        return userService.getUsers();
    }
    @GetMapping("/{id}")
    UserDto getUser(@PathVariable Integer id){
        return userService.getUser(id);
    }
    @PostMapping
    UserDto saveUser(@RequestBody UserDto userDto){
        return userService.saveUser(userDto);
    }
    @PutMapping("/{id}")
    UserDto updateUser(@PathVariable Integer id, @RequestBody UserDto userDto){
        return userService.updateUser(id, userDto);
    }
    @DeleteMapping("/{id}")
    String deleteUser(@PathVariable Integer id){return userService.deleteUser(id);}
}
