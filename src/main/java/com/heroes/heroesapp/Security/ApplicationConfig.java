package com.heroes.heroesapp.Security;


import com.heroes.heroesapp.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
@RequiredArgsConstructor
public class ApplicationConfig
{
    private final UserRepository _userRepos;
    @Bean
    public UserDetailsService userDetailsService()
    {
        return _userRepos::GetUserByLogin;
    }

}
