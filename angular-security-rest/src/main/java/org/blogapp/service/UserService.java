package org.blogapp.service;

import org.blogapp.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User save(User user);

    void updateUsersToken(String token, User user);

    User findByToken(String token);
}
