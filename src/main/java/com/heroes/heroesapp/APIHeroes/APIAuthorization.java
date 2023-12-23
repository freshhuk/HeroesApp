package com.heroes.heroesapp.APIHeroes;

import com.heroes.heroesapp.Domain.Models.LoginRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.heroes.heroesapp.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.heroes.heroesapp.Domain.Entity.User;

import java.util.Optional;

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
    @PostMapping("/login")
    public String UserLogin(@RequestBody LoginRequest loginDTO)
    {
        // Проверить, есть ли такой пользователь в базе данных
        Optional<User> user = userRepository.GetUserByLogin(loginDTO.getLogin());
        if (user.isEmpty())
        {
            // Пользователь не найден
            return "User not found";
        }
        // Пользователь найден, сделать его авторизованным
       Authentication authentication = new UsernamePasswordAuthenticationToken
                (
                user.get().getUsername(),
                loginDTO.getPassword(),
                user.get().getAuthorities()
                );

       SecurityContextHolder.getContext().setAuthentication(authentication);
        return "Пользователь авторизован";
    }

}
