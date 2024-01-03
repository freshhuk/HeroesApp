package com.heroes.heroesapp.Services;

import com.heroes.heroesapp.Domain.Entity.User;
import com.heroes.heroesapp.Domain.Models.LoginRequest;
import com.heroes.heroesapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutorizationService
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public AutorizationService(UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String UserLogin(LoginRequest loginModel)
    {
        if(loginModel == null)
        {
            return "Not fond";
        }
        Optional<User> user = userRepository.GetUserByLogin(loginModel.getLogin());
        // Пользователь не найден
        if (user.isEmpty())
        {
            return "User not found";
        }
        // Пользователь найден, сделать его авторизованным
        Authentication authentication = new UsernamePasswordAuthenticationToken
                (
                        user.get().getUsername(),
                        loginModel.getPassword(),
                        user.get().getAuthorities()
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "Done";
    }

    public String UserRegister(User user)
    {
        if(user != null)
        {
            Optional<User> userDb = userRepository.GetUserByLogin(user.getLogin());
            if(userDb.isEmpty())
            {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepository.Add(user);
                Authentication authentication = new UsernamePasswordAuthenticationToken
                        (
                                user.getUsername(),
                                user.getPassword(),
                                user.getAuthorities()
                        );

                SecurityContextHolder.getContext().setAuthentication(authentication);
                return "Done";
            }
            return "User had been created";
        }
        return "Model is null";
    }
}
