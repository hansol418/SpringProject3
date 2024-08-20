package com.busanit501.springproject3.Controller;

import com.busanit501.springproject3.Service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        String token = userService.registerUser(request.getUsername(), request.getPassword(), request.getEmail());
        return ResponseEntity.ok(token);
    }
}

@Data
class RegisterRequest {
    private String username;
    private String password;
    private String email;

    // Getters and Setters
    // ...
}


