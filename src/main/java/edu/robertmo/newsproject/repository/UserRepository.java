package edu.robertmo.newsproject.repository;

import edu.robertmo.newsproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
