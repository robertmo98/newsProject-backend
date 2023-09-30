package edu.robertmo.newsproject.repository;

import edu.robertmo.newsproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
