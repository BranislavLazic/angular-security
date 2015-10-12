package org.blogapp.authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.blogapp.domain.User;
import org.blogapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenHandler {

    public static final String SECRET = "secret";

    @Autowired
    private UserService userService;

    public UserDetails getUserByToken(String token) {
        String username = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return userService.loadUserByUsername(username);
    }

    public String createTokenForUser(User user) {
        String token = Jwts.builder().setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .setIssuedAt(new Date())
                .compact();

        userService.updateUsersToken(token, user);

        return token;
    }

}
