package com.auth.auth.controller;


import com.auth.auth.model.User;
import com.auth.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    private AuthenticationManager authenticationManager;

    @GetMapping("/dashboard")
    public String dashboard(){
        return "dashboard_page";
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {//@CurrentUser User user
        String username = user.getUsername();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, user.getPassword()));
        return new ResponseEntity<>(username + user.getPassword(), HttpStatus.OK);    }

    @GetMapping("/register")
    public String registerPage(){
        return "register_page";
    }
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
            System.out.println("Register request: " + user);
            User registeredUser = userService.handleRegister(user);
            return ResponseEntity.ok(registeredUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> users()  {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/admin")
    public String admin(Model model)
    {
        return "admin_page";
    }
}
