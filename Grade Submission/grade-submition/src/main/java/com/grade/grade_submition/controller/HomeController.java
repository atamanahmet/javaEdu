package com.grade.grade_submition.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HomeController {
    @GetMapping("/")
    public String getIndex() {
        return new String("Home");
    }

}
