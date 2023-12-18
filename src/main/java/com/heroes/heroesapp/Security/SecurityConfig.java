package com.heroes.heroesapp.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{
    private final JWTAuthFilter jwtAuthFilter;


    @Autowired
    public SecurityConfig(JWTAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Используйте новый вариант для отключения CSRF
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Используйте новый вариант для безсессионного режима
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/public/**").permitAll() // Разрешаем доступ к папке public
                        .requestMatchers("/register").permitAll() // Разрешаем доступ к методу регестрации
                        .anyRequest().authenticated()) // Все остальные запросы требуют аутентификации
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // Добавляем ваш фильтр


    }

}
