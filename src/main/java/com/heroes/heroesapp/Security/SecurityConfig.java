package com.heroes.heroesapp.Security;

import com.heroes.heroesapp.Domain.Entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder)
    {
        //created user
        UserDetails admin1 = org.springframework.security.core.userdetails.User.builder().username("user1").
                password(encoder.encode("user")).build();
        //UserDetails admin = User.builder().login("user").password(encoder.encode("user")).build();
        return new InMemoryUserDetailsManager(admin1);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)//Защита от подмены пользователя
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(new AntPathRequestMatcher("/api/auth/welcome")).permitAll()//Доступ по адрему всем
                        .requestMatchers(new AntPathRequestMatcher("/api/**")).authenticated())//Доступ к маршрутам начинающихся с /апи под аунтнентификацияй
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)//Доступ всем к окну регестрации
                .build();

    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
