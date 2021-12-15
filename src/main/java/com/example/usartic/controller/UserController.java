package com.example.usartic.controller;

import com.example.usartic.entity.UserEntity;
import com.example.usartic.exeption.UserNotFoundException;
import com.example.usartic.exeption.UserAlreadyExistException;
import com.example.usartic.service.UserService;
import com.example.usartic.service.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    final
    UserService userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping
    public ResponseEntity getUser(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(userService.getUser(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/older")
    public ResponseEntity getUserOlder(@RequestParam int age) {
        try {
            return ResponseEntity.ok(userService.getUsersOlder(age));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/color")
    public ResponseEntity getUsersByArticleColor(@RequestParam String color) {
        try {
            return ResponseEntity.ok(userService.getUsersByArticleColor(color));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/articles-more")
    public ResponseEntity getUsersWithMoreArticles(@RequestParam int value) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUserWithMoreArticles(value));
    }

    @PostMapping("/save")
    public ResponseEntity saveUser(@RequestBody UserEntity user) {
        try {
            userService.saveUser(user);
            return ResponseEntity.ok("user " + user.getName() + " saved");
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok("user wos deleted " + userService.deleteUser(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
