package edu.robertmo.newsproject.repository;

import edu.robertmo.newsproject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
