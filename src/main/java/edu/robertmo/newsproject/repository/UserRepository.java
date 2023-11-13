package edu.robertmo.newsproject.repository;

import edu.robertmo.newsproject.entity.Role;
import edu.robertmo.newsproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameIgnoreCaseOrEmailIgnoreCase(String username, String email);
    Optional<User> findByUsernameIgnoreCase(String  username);
    Optional<User> findByEmailIgnoreCase(String email);


    //check if user exists for validation purposes:
    Boolean existsUserByUsernameIgnoreCase(String username);
    Boolean existsUserByEmailIgnoreCase(String email);
}
