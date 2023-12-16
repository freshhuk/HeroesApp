package com.heroes.heroesapp.Security;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter
{
    private final JwtService jwtService; //own jwt_service
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException
    {
        // we extract header in request where header "Authorization"
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userLogin;
        //if we have no header or this header don't have tag Bearer we skip this request
        if( authHeader == null || !authHeader.startsWith("Bearer "))
        {
            filterChain.doFilter(request, response);
            return;
        }
        //if we have tag and header we extract jwt token
        jwt = authHeader.substring(7);
        userLogin = jwtService.ExtractUserLogin(jwt);
    }
}
