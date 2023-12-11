package com.heroes.heroesapp.Security;

import com.heroes.heroesapp.Domain.Interface.IRepository;
import com.heroes.heroesapp.Domain.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.heroes.heroesapp.Domain.Entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserJWTService implements IUserService
{
    private final IRepository<User> _user_repos;
    private final String SECRET_KEY = "fgggfrewygregreGiko";
    @Autowired
    public UserJWTService(IRepository<User>  user_repos)
    {
        _user_repos = user_repos;
    }


}
