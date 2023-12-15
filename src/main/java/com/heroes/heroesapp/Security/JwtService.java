package com.heroes.heroesapp.Security;

import com.heroes.heroesapp.Domain.Interface.IRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import com.heroes.heroesapp.Domain.Entity.User;
import org.springframework.stereotype.Service;

@Service
public class JwtService
{
    private final IRepository<User> _user_repos;
    private static final String SECRET_KEY = "225AEF27483C5A8D6419DAB96DBDD";
    @Autowired
    public JwtService(IRepository<User>  user_repos)
    {
        _user_repos = user_repos;
    }


    public String ExtractUserEmail(String jwt_token)
    {
        return null;
    }
    private Claims extractAllClaims(String jwt_token)
    {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJwt(jwt_token).getBody();
    }
}
