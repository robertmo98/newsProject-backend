package edu.robertmo.newsproject.repository;

import edu.robertmo.newsproject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByNameIgnoreCase(String role);
    Role findByName(String name);
    boolean existsByName(String name);

}
