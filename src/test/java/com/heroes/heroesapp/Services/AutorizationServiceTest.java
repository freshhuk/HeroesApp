package com.heroes.heroesapp.Services;


import com.heroes.heroesapp.Domain.Enum.Role;
import com.heroes.heroesapp.Repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.heroes.heroesapp.Domain.Models.LoginRequest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.heroes.heroesapp.Domain.Entity.User;

import java.util.Optional;

public class AutorizationServiceTest
{
    @InjectMocks
    AutorizationService authService;

    @Mock
    UserRepository user_repos;

    @Test
    void UserLoginTest()
    {
        LoginRequest model = new LoginRequest();
        {
            model.setLogin("test");
            model.setPassword("test");
        }
        User user_model = new User();
        {
            user_model.setLogin("test");
            user_model.setPassword("test");
            user_model.setRole(Role.DEFAULT_USER);
        }
        Mockito.when(user_repos.GetUserByLogin("test")).thenReturn(Optional.of(user_model));

        var result = authService.UserLogin(model);

        String massage = "Done";
        Assertions.assertEquals(result,massage);

    }
}
