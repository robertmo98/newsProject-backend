package edu.robertmo.newsproject.service;

import edu.robertmo.newsproject.dto.ArticleRequestDto;
import edu.robertmo.newsproject.dto.ArticleResponseDto;
import edu.robertmo.newsproject.entity.Article;
import edu.robertmo.newsproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;
    @Override
    public ArticleResponseDto createArticle(ArticleRequestDto dto) {
        Article entity = modelMapper.map(dto, Article.class);
        entity.setDate(LocalDate.now());
        var saved = articleRepository.save(entity);

        return modelMapper.map(saved, ArticleResponseDto.class);
    }

    @Override
    public List<ArticleResponseDto> getAllArticles() {
        return articleRepository
                .findAll()
                .stream()
                .map(a -> modelMapper.map(a, ArticleResponseDto.class))
                .toList();
    }

    @Override
    public ArticleResponseDto getArticleById(long id) {
        return modelMapper.map(getArticleEntity(id), ArticleResponseDto.class);
    }

    @Override
    public ArticleResponseDto updateArticleById(ArticleRequestDto dto, long id) {
        // TODO: 30/09/2023 set this later. remember to handle the time property issue
        return null;
    }

    @Override
    public ArticleResponseDto deleteArticleById(long id) {
        Article article = getArticleEntity(id);
        articleRepository.deleteById(id);
        return modelMapper.map(article, ArticleResponseDto.class);
    }




    private Article getArticleEntity(long id) {
        return articleRepository.findById(id).orElseThrow();
        // TODO: 30/09/2023 configure resourceNotFoundException and throw it
    }
}
