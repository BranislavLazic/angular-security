package org.angularsecurity.service;

import org.angularsecurity.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User save(User user);

    void updateUsersToken(String token, User user);

    User findByToken(String token);
}
