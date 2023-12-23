package com.heroes.heroesapp.APIHeroes;

import com.heroes.heroesapp.Domain.Models.LoginRequest;
import com.heroes.heroesapp.Services.AutorizationService;
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

    private final AutorizationService authService;
    @Autowired
    public APIAuthorization(AutorizationService authService)
    {
        this.authService = authService;
    }


    @PostMapping("/register")
    public String UserRegister(@RequestBody User user)
    {
        String responce = authService.UserRegister(user);
        return (responce.equals("Done")) ? "User is saved" : "User not saved";

    }
    //todo
    @PostMapping("/login")
    public String UserLogin(@RequestBody LoginRequest loginDTO)
    {
        String responce = authService.UserLogin(loginDTO);
        if(responce.equals("Not fond"))
        {
            return "login model null";
        }
        if(responce.equals("Done"))
        {
            return "User is login";
        }
        else {
            return "error";
        }

    }

}
