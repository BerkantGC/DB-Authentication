package com.auth.auth.controller;


import com.auth.auth.model.User;
import com.auth.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/dashboard")
    public String dashboard(){
        return "dashboard_page";
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> users()  {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/login")
    public String login(){
        return "login_page";
    }

    @GetMapping("/admin")
    public String admin(Model model)
    {
        return "admin_page";
    }
}
