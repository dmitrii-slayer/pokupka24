package org.akatsuki.pokupka24.controller;

import org.akatsuki.pokupka24.domain.entity.User;
import org.akatsuki.pokupka24.domain.entity.UserAccount;
import org.akatsuki.pokupka24.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping(UserController.RESOURCE_PATH)
@RestController
public class UserController {

    private static final String BASE_PATH = "/api/pokupka24";
    private static final String API_VERSION = "/v1";
    private static final String RESOURCE = "/users";
    private static final String ACCOUNTS = "/accounts";

    public static final String RESOURCE_PATH = BASE_PATH + API_VERSION + RESOURCE;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{userId}")
    public User findUserById(@PathVariable("userId") UUID userId) {
        return userService.findUserById(userId);
    }

    @PostMapping("/")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable("userId") UUID userId,
                           @RequestBody User user) {
        return userService.updateUser(userId, user);
    }

    @GetMapping("/{userId}" + ACCOUNTS)
    public List<UserAccount> findUserAccountsByUserId(@PathVariable("userId") UUID userId) {
        return userService.findUserAccounts(userId);
    }
}
