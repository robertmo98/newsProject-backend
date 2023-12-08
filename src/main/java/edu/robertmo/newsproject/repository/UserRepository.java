package edu.robertmo.newsproject.repository;

import edu.robertmo.newsproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameIgnoreCaseOrEmailIgnoreCase(String username, String email);
    Optional<User> findByUsernameIgnoreCase(String  username);
    Optional<User> findByEmailIgnoreCase(String email);
    User findByUsername(String username);


    /** check if a user exists for validation purposes **/
    Boolean existsUserByUsernameIgnoreCase(String username);
    Boolean existsUserByEmailIgnoreCase(String email);
}
