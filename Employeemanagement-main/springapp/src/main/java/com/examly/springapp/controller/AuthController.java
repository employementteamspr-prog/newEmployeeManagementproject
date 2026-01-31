package com.examly.springapp.controller;
import java.util.Map;

import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;
import com.examly.springapp.service.UserService;
import com.examly.springapp.utils.JwtUtil;

import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepo userRepo;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String,String> body){
        String email = body.get("email");
        String password = body.get("password");

        if(userRepo.findByEmail(email).isPresent()){
            return new ResponseEntity<>("Email already exists", CONFLICT);
        }

        userService.createUser(
            User.builder()
                .email(email)
                .password(passwordEncoder.encode(password)) // encode password
                .build()
        );

        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> loginUser(@RequestBody Map<String,String> body){
        String email = body.get("email");
        String password = body.get("password");

        var userOpt = userRepo.findByEmail(email);

        if(userOpt.isEmpty()){
            return new ResponseEntity<>(Map.of("error", "User not registered"), UNAUTHORIZED);
        }

        User user = userOpt.get();

        if(!passwordEncoder.matches(password, user.getPassword())){
            return new ResponseEntity<>(Map.of("error", "Invalid credentials"), UNAUTHORIZED);
        }

        String token = jwtUtil.generateToken(email);
        return new ResponseEntity<>(Map.of("token", token), HttpStatus.OK);
    }
}
