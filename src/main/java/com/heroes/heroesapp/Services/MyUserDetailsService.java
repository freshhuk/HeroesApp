package com.heroes.heroesapp.Services;

import com.heroes.heroesapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.heroes.heroesapp.Domain.Entity.User;
import com.heroes.heroesapp.Security.MyUserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService
{

    @Autowired
    private UserRepository userRepos;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<User> user = userRepos.GetUserByLogin(username);
        return user.map(MyUserDetails::new).orElseThrow(()->new UsernameNotFoundException(username + "Not found"));
    }
}
