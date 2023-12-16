package com.heroes.heroesapp.Security;

import com.heroes.heroesapp.Domain.Interface.IRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.heroes.heroesapp.Domain.Entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.function.Function;

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


    public String ExtractUserLogin(String jwt_token)
    {
        return extractClaim(jwt_token, Claims::getSubject);
    }
    public String GenerationToken(UserDetails userDetails)
    {
        return GenerationToken(new HashMap<>(), userDetails);
    }
    public boolean isTokenValid(String jwt_token, UserDetails userDetails)
    {
        final String userEmail = ExtractUserLogin(jwt_token);
        return (userEmail.equals(userDetails.getUsername())) && !isTokenExpired(jwt_token);
    }

    private boolean isTokenExpired(String jwt_token)
    {
        return extractExpiration(jwt_token).before(new Date());
    }

    public String GenerationToken(Map<String, Object> extraClaims, UserDetails userDetails)
    {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 *60 *24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public <T> T extractClaim(String jwt_token, Function<Claims, T> claimsResolver)
    {
        final Claims claims = extractAllClaims(jwt_token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwt_token)
    {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJwt(jwt_token).getBody();
    }
    private Key getSignInKey()
    {

        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY); //decode own key in byte
        return Keys.hmacShaKeyFor(keyBytes);
    }
    private  Date extractExpiration(String jwt_token)
    {
        return extractClaim(jwt_token, Claims::getExpiration);
    }
}
