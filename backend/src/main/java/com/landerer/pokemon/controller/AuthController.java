package com.landerer.pokemon.controller;

import com.landerer.pokemon.dto.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/v01")
public class AuthController {

    private static final Map<String, User> users = new ConcurrentHashMap<>();

    @PostMapping("/signup")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        if(user == null ||
                user.getUsername() == null || user.getUsername().length() == 0 ||
                user.getPassword() == null || user.getPassword().length() == 0 ||
                users.containsKey(user.getUsername())) {
            return ResponseEntity.badRequest().build();
        }
        users.put(user.getUsername(), user);
        return ResponseEntity.ok(user);
    }
}
