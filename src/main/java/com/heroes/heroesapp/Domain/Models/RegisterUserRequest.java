package com.heroes.heroesapp.Domain.Models;

import com.heroes.heroesapp.Domain.Enum.Role;

import lombok.Data;

@Data

public class RegisterUserRequest
{
    private String login;
    private String password;
    private String email;
    private Role role;
}
