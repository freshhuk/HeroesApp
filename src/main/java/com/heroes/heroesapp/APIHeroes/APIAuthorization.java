package com.heroes.heroesapp.APIHeroes;

import com.heroes.heroesapp.Domain.Models.RegisterUserRequest;
import com.heroes.heroesapp.Domain.Models.RegisterResponse;

import com.heroes.heroesapp.Repository.UserRepository;
import com.heroes.heroesapp.Security.JwtService;
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
    private final JwtService jwtService;

    @Autowired
    public APIAuthorization(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }
    @PostMapping("/login")
    public ResponseEntity<String> UserLogin()
    {
        return null;
    }


    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> UserRegister(@RequestBody RegisterUserRequest register_model)
    {
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
        return ResponseEntity.ok(response);
    }



    @PostMapping("/logout")
    public String UserLogout()
    {
        return "Successful";
    }

}
