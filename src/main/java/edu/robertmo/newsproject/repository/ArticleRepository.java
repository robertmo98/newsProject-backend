package edu.robertmo.newsproject.repository;

import edu.robertmo.newsproject.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
