package exercise.controller;

import exercise.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import exercise.service.UserService;


@RestController
@RequestMapping("/users")
public class UsersController {

    private static final String ID = "/{id}";

    @Autowired
    private UserService userService;

    @GetMapping(path = "")
    public Flux<User> getUsers() {
        return userService.findAll();
    }

    // BEGIN
    @GetMapping(ID)
    public Mono<User> getUserById(@PathVariable Integer id) {
        return userService.findUserById(id);
    }

    @PostMapping
    public Mono<User> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PatchMapping(ID)
    public Mono<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
        return userService.updateUserById(id, user);
    }

    @DeleteMapping(ID)
    public Mono<Void> deleteUser(@PathVariable Integer id) {
        return userService.deleteUserById(id);
    }
    // END
}
