package com.auth.auth.controller;


import com.auth.auth.model.User;
import com.auth.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public String dashboard(){
        return "login succesful dashboard page";
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> users()  {
        return ResponseEntity.ok(userService.getAll());
    }

    /*@PostMapping("/register")
    public void saveUser() throws Exception {
        userService.save();
    }*/

    @GetMapping("/admin")
    public String admin(Model model)
    {
        return "admin_page";
    }
}
