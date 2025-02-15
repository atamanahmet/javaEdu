package com.web.web_app;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {

    private List<User> userList = new ArrayList<>();

    @DateTimeFormat(pattern = "yyyy,MM,dd")
    private LocalDateTime currentDate;

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false, value = "id") String id) {

        User user = new User();

        model.addAttribute("user", user);

        return "sign-up.html";
    }

    @PostMapping("/submitItem")
    public String handleSubmit(@Valid User user, BindingResult result) {

        if (result.hasErrors()) {
            return "sign-up.html";
        }
        if (user.getFirstName().equals(user.getLastName())) {
            result.rejectValue("lastName", "", "Please enter valid data");
            return "sign-up.html";
        }
        userList.add(user);

        return "result.html";
    }

}
