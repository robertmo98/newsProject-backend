package edu.robertmo.newsproject.repository;

import edu.robertmo.newsproject.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findArticleByTitleIgnoreCase(String title);

}
