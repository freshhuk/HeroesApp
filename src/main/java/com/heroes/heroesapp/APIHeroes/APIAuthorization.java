package com.heroes.heroesapp.APIHeroes;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;

import com.heroes.heroesapp.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.heroes.heroesapp.Domain.Entity.User;

@Service
@RestController
@RequestMapping("/api/auth")
public class APIAuthorization
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public APIAuthorization(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping("/welcome")
    public String TestAuth()
    {
        return "welcome";
    }
    @PostMapping("/register")
    public String UserRegister(@RequestBody User user)
    {
        if(user != null)
        {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.Add(user);
            return "User is saved";
        }
        return "User not saved";
    }
}
