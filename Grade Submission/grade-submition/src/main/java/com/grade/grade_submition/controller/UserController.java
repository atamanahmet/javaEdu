package com.grade.grade_submition.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.grade.grade_submition.domain.User;
import com.grade.grade_submition.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RestController;
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
        // System.out.println("register post making");
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        userService.saveUser(user);
        return new ResponseEntity<>("User registered succesfully", HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getUser(@PathVariable(required = true, value = "userId") Long userId) {
        if (userService.isExistsById(userId)) {
            return new ResponseEntity<>(userService.findUserById(userId).get().getUsername(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/user/{userId}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable(required = true, value = "userId") Long userId)
            throws Exception {

        userService.deleteUserById(userId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
