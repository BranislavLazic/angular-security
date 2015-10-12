package org.blogapp.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.blogapp.authentication.TokenHandler;
import org.blogapp.domain.LoginResponse;
import org.blogapp.domain.User;
import org.blogapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TokenHandler tokenHandler;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public LoginResponse login(@RequestBody User user) {
        UserDetails userDetails = userService.loadUserByUsername(user.getUsername());

        if(userDetails.getPassword().equals(user.getPassword())) {

            String token = tokenHandler.createTokenForUser(user);

            return new LoginResponse(token);
        } else {
            return new LoginResponse(null);
        }
    }


}
