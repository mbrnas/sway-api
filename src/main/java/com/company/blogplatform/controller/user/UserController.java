package com.company.blogplatform.controller.user;

import com.company.blogplatform.model.users.User;
import com.company.blogplatform.service.user.UserServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user id");
        } else {
            User user = userService.getUserById(userId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @GetMapping("/all")
    public Page<User> getAllUsers(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String sort) {
        return userService.getAllUsers(pageNumber, pageSize, sort);
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (user == null) {
            throw new IllegalArgumentException("Invalid user data");
        } else {
            User createdUser = userService.addUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        }
    }

    @PutMapping("/update-user/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @RequestParam Long userId) {
        if (user == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user data");
        }
        User updatedUser = userService.updateUser(user, userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }


    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable Long userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user id");
        } else {
            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

}
