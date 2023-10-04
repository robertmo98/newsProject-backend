package edu.robertmo.newsproject.validators;

import edu.robertmo.newsproject.entity.Article;
import edu.robertmo.newsproject.repository.ArticleRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UniqueTitleValidator implements ConstraintValidator<UniqueTitle, String > {
    private final ArticleRepository articleRepository;

    @Override
    public boolean isValid(String title, ConstraintValidatorContext context) {
        Optional<Article> article =articleRepository.findArticleByTitleIgnoreCase(title);

        //if title does not exist -> valid
        return article.isEmpty();
    }
}
