package com.heroes.heroesapp.Services;
import com.heroes.heroesapp.Domain.Enum.Role;
import org.junit.jupiter.api.Assertions;
import com.heroes.heroesapp.Domain.Entity.User;
import org.junit.jupiter.api.Test;
import com.heroes.heroesapp.Repository.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;

@ExtendWith(MockitoExtension.class)
public class MyUserDetailsServiceTest
{
    @InjectMocks
    MyUserDetailsService userDetailsService;
    @Mock
    UserRepository userRepository;
    @Test
    void loadUserByUsername_UserFound_ReturnsUserDetailsTest()
    {
        String UserName = "test";
        User model = new User();
        {
            model.setLogin("test");
            model.setPassword("test");
            model.setRole(Role.DEFAULT_USER);
        }

        Mockito.when(userRepository.GetUserByLogin(UserName)).thenReturn(Optional.of(model));


        UserDetails userDetails = userDetailsService.loadUserByUsername(UserName);


        Assertions.assertEquals(model.getUsername(), userDetails.getUsername());
    }
    @Test
    void loadUserByUsername_UserNotFoundTest()
    {
        String UserName = "test";

        Mockito.when(userRepository.GetUserByLogin(UserName)).thenReturn(Optional.empty());


        Assertions.assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(UserName));
    }

}
