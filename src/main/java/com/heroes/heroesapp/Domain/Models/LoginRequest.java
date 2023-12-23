package com.heroes.heroesapp.Domain.Models;

import lombok.Data;

@Data
public class LoginRequest
{
    private String login;
    private String password;
}
