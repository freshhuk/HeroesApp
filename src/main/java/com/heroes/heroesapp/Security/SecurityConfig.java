package com.heroes.heroesapp.Security;

import com.heroes.heroesapp.Services.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity

public class SecurityConfig
{

    //Регестрируем наш UserDetailsService и указываем что б он возвращал наш MyUserDetailsService
    @Bean
    public UserDetailsService userDetailsService()
    {
        return new MyUserDetailsService();
    }

    //Регестриуем наши настройки безопасности
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)//Защита от подмены пользователя
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(new AntPathRequestMatcher("/api/auth/**")).permitAll()//Маршруты разрешены без аунтентификации
                        .requestMatchers(new AntPathRequestMatcher("/api/app/**")).authenticated())//Доступ к маршрутам начинающихся с /app под аунтнентификацияй

                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)//Доступ всем к окну регестрации
                .build();

    }
    //Регестрируем наш провайдер с нашими класами
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    //Регестрируем наш PasswordEncoder как бин что б мы могли его потом использовать в других класах
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
