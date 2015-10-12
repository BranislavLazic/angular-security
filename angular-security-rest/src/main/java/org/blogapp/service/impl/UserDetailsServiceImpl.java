package org.blogapp.service.impl;

import org.blogapp.domain.User;
import org.blogapp.repository.UserRepository;
import org.blogapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found!");
        }

        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole().toString());

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Arrays.asList(grantedAuthority));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void updateUsersToken(String token, User user) {
       userRepository.updateUsersToken(token, user.getUsername());
    }

    @Override
    public User findByToken(String token) {
        return userRepository.findByToken(token);
    }
}
