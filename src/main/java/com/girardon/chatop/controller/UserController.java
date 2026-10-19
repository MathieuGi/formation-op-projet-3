package com.girardon.chatop.controller;

import com.girardon.chatop.model.User;
import com.girardon.chatop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/api/auth/register")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        try {
            userService.saveUser(user);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.ok("jwt");
    }
}
