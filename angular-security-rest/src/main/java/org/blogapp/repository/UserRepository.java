package org.blogapp.repository;

import org.blogapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

    User findByUsername(String username);

    @Modifying
    @Query("update User u set u.token = ?1 where u.username = ?2")
    void updateUsersToken(String token, String user);

    User findByToken(String token);
}
