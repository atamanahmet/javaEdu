package com.grade.grade_submition.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import com.grade.grade_submition.domain.User;
import com.grade.grade_submition.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/signup")
    public String getForm() {
        return new String(
                "To register: POST 127.0.0.1:8080/register, Body={\"name\":\"{yourName}\",\"username\":\"{yourUsername}\",\"password\":\"{yourPassword}\"}");
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getMethodName() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @PostMapping("/user/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        if (userService.findUserByUserName(user.getUsername()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }
        userService.saveUser(user);
        return new ResponseEntity<>("User registered succesfully", HttpStatus.CREATED);
    }

    // @PostMapping("/login")
    // public ResponseEntity<Object> loginAuth(@Valid @RequestBody User user,
    // BindingResult result) {
    // if (result.hasErrors()) {
    // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }
    // return new ResponseEntity<>(HttpStatus.OK);
    // }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable(required = true, value = "userId") Long userId) {
        // if (auth == true) {
        if (userService.isExistsById(userId)) {
            return new ResponseEntity<>(userService.findUserById(userId), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        // }

    }

    @DeleteMapping("/delete/user/{userId}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable(required = true, value = "userId") Long userId)
            throws Exception {

        userService.deleteUserById(userId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
