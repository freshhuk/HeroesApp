package com.heroes.heroesapp.APIHeroes;

import com.heroes.heroesapp.Domain.Models.RegisterUserRequest;
import com.heroes.heroesapp.Domain.Models.RegisterResponse;
import org.springframework.web.bind.annotation.GetMapping;

import com.heroes.heroesapp.Repository.UserRepository;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    public APIAuthorization(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @PostMapping("/login")
    public ResponseEntity<String> UserLogin()
    {
        return null;
    }

    @GetMapping("/welcome")
    public String TestAuth()
    {
        return "welcome";
    }
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> UserRegister()
    {
        /*
        if (register_model == null)
        {
            return ResponseEntity.badRequest().build();
        }
        User user = new User();
        user.setLogin(register_model.getLogin());
        user.setEmail(register_model.getEmail());
        user.setRole(register_model.getRole());
        user.setPassword(register_model.getPassword());
        userRepository.Add(user);

        String token = jwtService.GenerationToken(user);
        RegisterResponse response = new RegisterResponse(token);
        return ResponseEntity.ok(response);*/
        return null;
    }



    @PostMapping("/logout")
    public String UserLogout()
    {
        return "Successful";
    }

}
