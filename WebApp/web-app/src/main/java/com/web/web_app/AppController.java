package com.web.web_app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AppController {

    private List<User> userList = new ArrayList<>();

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false, value = "id") String id) {

        User user = new User();

        model.addAttribute("user", user);

        return "sign-up.html";
    }

    @PostMapping("/")
    public String submitItem(@Valid User user, BindingResult result) {

        if (result.hasErrors()) {
            return "sign-up.html";
        }
        if (user.getFirstName().equals(user.getLastName())) {

        }

        userList.add(user);

        System.out.println(userList);

        return "result.html";
    }

}
