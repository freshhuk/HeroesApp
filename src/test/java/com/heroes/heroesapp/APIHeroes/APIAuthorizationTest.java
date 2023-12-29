package com.heroes.heroesapp.APIHeroes;
import com.heroes.heroesapp.Domain.Enum.Role;
import com.heroes.heroesapp.Domain.Models.LoginRequest;
import com.heroes.heroesapp.Services.AutorizationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.heroes.heroesapp.Domain.Entity.User;

@ExtendWith(MockitoExtension.class)
public class APIAuthorizationTest
{
    @InjectMocks
    APIAuthorization controller;
    @Mock
    AutorizationService mock_authservice;

    //Default test
    @Test
    void UserRegisterTest()
    {
        User test_model = new User();
        {
            test_model.setLogin("test123");
            test_model.setPassword("test");
            test_model.setRole(Role.DEFAULT_USER);
        }

        Mockito.when(mock_authservice.UserRegister(test_model)).thenReturn("Done");

        var result =  controller.UserRegister(test_model);
        String massage = "User is saved";
        Assertions.assertEquals(result, massage);
    }

    @Test
    void UserLoginTest()
    {
        LoginRequest test_model = new LoginRequest();
        {
            test_model.setLogin("test123");
            test_model.setPassword("test");
        }
        Mockito.when(mock_authservice.UserLogin(test_model)).thenReturn("Done");

        var result = controller.UserLogin(test_model);

        String massage = "User is login";
        Assertions.assertEquals(result, massage);

    }
    //Test when model is null
    @Test
    void NullUserRegisterTest()
    {
        Mockito.when(mock_authservice.UserRegister(null)).thenReturn("Model is null");

        var result =  controller.UserRegister(null);
        String massage = "User not saved";
        Assertions.assertEquals(result, massage);
    }

    @Test
    void NullUserLoginTest()
    {
        Mockito.when(mock_authservice.UserLogin(null)).thenReturn("Not fond");

        var result = controller.UserLogin(null);

        String massage = "login model null";
        Assertions.assertEquals(result, massage);

    }

    //Other test
    @Test
    void UserRegisterWhenUserHadBeenCreatedTest()
    {
        User test_model = new User();
        {
            test_model.setLogin("old");
            test_model.setPassword("test");
            test_model.setRole(Role.DEFAULT_USER);
        }
        User modelInDb = new User();
        {
            modelInDb.setLogin("old");
            modelInDb.setPassword("test");
            modelInDb.setRole(Role.DEFAULT_USER);
        }

        Mockito.when(mock_authservice.UserRegister(modelInDb)).thenReturn("User had been created");

        var result =  controller.UserRegister(test_model);
        String massage = "User had been created";
        Assertions.assertEquals(result, massage);
    }
    @Test
    void UserLoginWhenUserNotFoundTest()
    {
        LoginRequest test_model = new LoginRequest();
        {
            test_model.setLogin("TestTest");
            test_model.setPassword("ultrapassword");
        }
        Mockito.when(mock_authservice.UserLogin(test_model)).thenReturn("User not found");

        var result = controller.UserLogin(test_model);

        String massage = "error";
        Assertions.assertEquals(result, massage);

    }

}
