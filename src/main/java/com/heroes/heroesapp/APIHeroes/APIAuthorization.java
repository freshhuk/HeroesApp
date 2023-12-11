package com.heroes.heroesapp.APIHeroes;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Service
public class APIAuthorization
{
    @PostMapping("/login")
    public String UserLogin()
    {
        return "Successful";
    }
    @PostMapping("/register")
    public String UserRegister()
    {
        return "Successful";
    }
    @PostMapping("/logout")
    public String UserLogout()
    {
        return "Successful";
    }

}
