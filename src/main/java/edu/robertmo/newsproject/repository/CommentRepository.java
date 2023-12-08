package edu.robertmo.newsproject.repository;

import edu.robertmo.newsproject.entity.Comment;
import edu.robertmo.newsproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByArticleId(long articleId);
    List<Comment> findCommentsByUser(User user);
}
