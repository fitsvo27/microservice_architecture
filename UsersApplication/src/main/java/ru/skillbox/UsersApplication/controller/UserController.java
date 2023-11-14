package ru.skillbox.UsersApplication.controller;

import org.springframework.web.bind.annotation.*;
import ru.skillbox.UsersApplication.model.User;
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
    List<User> getUsers(){
        return userService.getUsers();
    }
    @GetMapping("/{id}")
    User getUser(@PathVariable Integer id){
        return userService.getUser(id);
    }
    @PostMapping
    User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }
    @PutMapping("/{id}")
    User updateUser(@PathVariable Integer id, @RequestBody User user){
        return userService.updateUser(id, user);
    }
    @DeleteMapping("/{id}")
    String deleteUser(@PathVariable Integer id){return userService.deleteUser(id);}
}
