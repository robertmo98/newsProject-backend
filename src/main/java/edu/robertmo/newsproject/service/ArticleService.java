package edu.robertmo.newsproject.service;

import edu.robertmo.newsproject.dto.ArticleRequestDto;
import edu.robertmo.newsproject.dto.ArticleResponseDto;

import java.util.List;

public interface ArticleService {
    ArticleResponseDto createArticle(ArticleRequestDto dto);
    List<ArticleResponseDto> getAllArticles();
    ArticleResponseDto getArticleById(long id);
    ArticleResponseDto updateArticleById(ArticleRequestDto dto, long id);
    ArticleResponseDto deleteArticleById(long id);

    // TODO: 30/09/2023 add methods to return articles using pagination

}
