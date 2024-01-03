package com.heroes.heroesapp.Services;


import com.heroes.heroesapp.Domain.Enum.Role;
import com.heroes.heroesapp.Repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.heroes.heroesapp.Domain.Models.LoginRequest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.heroes.heroesapp.Domain.Entity.User;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AutorizationServiceTest
{
    @InjectMocks
    AutorizationService authService;

    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    void UserLoginTest()
    {
        LoginRequest model = new LoginRequest();
        {
            model.setLogin("test");
            model.setPassword("test");
        }
        User model_user = new User();
        {
            model_user.setLogin("test");
            model_user.setPassword("test");
            model_user.setRole(Role.DEFAULT_USER);
        }
        Mockito.when(userRepository.GetUserByLogin(model.getLogin())).thenReturn(Optional.of(model_user));
        var result = authService.UserLogin(model);

        String massage = "Done";
        Assertions.assertEquals(result,massage);

    }
    @Test
    void UserRegisterTest()
    {
        User model_user = new User();
        {
            model_user.setLogin("test");
            model_user.setPassword("test");
            model_user.setRole(Role.DEFAULT_USER);
        }
        Mockito.when(userRepository.GetUserByLogin(model_user.getLogin())).thenReturn(Optional.empty());
        Mockito.when(passwordEncoder.encode(model_user.getPassword())).thenReturn("test_encode");
        var result = authService.UserRegister(model_user);

        String massage = "Done";
        Assertions.assertEquals(result,massage);
    }

    //Null tests
    @Test
    void UserLoginWhenLoginModelIsNullTest()
    {
        var result = authService.UserLogin(null);
        String massage = "Not fond";
        Assertions.assertEquals(result,massage);

    }
    @Test
    void UserRegisterWhenUserIsNullTest()
    {
        var result = authService.UserRegister(null);
        String massage = "Model is null";
        Assertions.assertEquals(result,massage);
    }
    //Other tests
    @Test
    void UserLoginWhenUserNotFoundTest()
    {
        LoginRequest model = new LoginRequest();
        {
            model.setLogin("test");
            model.setPassword("test");
        }

        Mockito.when(userRepository.GetUserByLogin(model.getLogin())).thenReturn(Optional.empty());
        var result = authService.UserLogin(model);

        String massage = "User not found";
        Assertions.assertEquals(result,massage);

    }
    @Test
    void UserRegisterWhenUserAlreadyCreatedTest()
    {
        User model_user = new User();
        {
            model_user.setLogin("test");
            model_user.setPassword("test");
            model_user.setRole(Role.DEFAULT_USER);
        }
        Mockito.when(userRepository.GetUserByLogin(model_user.getLogin())).thenReturn(Optional.of(model_user));
        var result = authService.UserRegister(model_user);

        String massage = "User had been created";
        Assertions.assertEquals(result,massage);
    }

}
