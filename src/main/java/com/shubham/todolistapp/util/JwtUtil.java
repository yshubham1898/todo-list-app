package com.shubham.todolistapp.util;

import com.shubham.todolistapp.data.TodoUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

//  --contains method to generateToken, validateToken, check expiration & issuer time
@Component
public class JwtUtil {


    //secret key is used to create a signature
    private String SECRET_KEY = "secret";

    //retrieve username from jwt token
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    //extract expiration date from jwt token
    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    //
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    //check if token has expired or not
    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }


    //the whole generation of token starts from here


    //this method will generate the token, using create method
    public String generateToken(TodoUser todoUser){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, todoUser.getUsername());
    }


    //1.    Define claims for token, like Issuer, Expiration, Subject and the
    //2.    Sign the jwt using HS512 algorithm and secret key
    private String createToken(Map<String, Object> claims, String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }


    //validate token
    public Boolean validateToken(String token, TodoUser todoUser){
        final String username = extractUsername(token);
        return (username.equals(todoUser.getUsername()) && !isTokenExpired(token));
    }


}
