package edu.robertmo.newsproject.service;

import edu.robertmo.newsproject.dto.ArticlePageResponseDto;
import edu.robertmo.newsproject.dto.ArticleRequestDto;
import edu.robertmo.newsproject.dto.ArticleResponseDto;
import edu.robertmo.newsproject.dto.ArticleWithCommentsDto;
import edu.robertmo.newsproject.entity.Article;
import edu.robertmo.newsproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        Article articleFromDb = getArticleEntity(id);

        //update => copy new props from request dto
        articleFromDb.setCategory(dto.getCategory());
        articleFromDb.setTitle(dto.getTitle());
        articleFromDb.setContent(dto.getContent());
        //save the original date.
        articleFromDb.setSecondaryTitle(dto.getSecondaryTitle());
        articleFromDb.setMainImg(dto.getMainImg());
        articleFromDb.setMainImgDescription(dto.getMainImgDescription());
        articleFromDb.setMainImgCredit(dto.getMainImgCredit());
        articleFromDb.setSecondImg(dto.getSecondImg());
        articleFromDb.setSecondImgDescription(dto.getSecondImgDescription());
        articleFromDb.setSecondImgCredit(dto.getSecondImgCredit());

        //save:
        var saved = articleRepository.save(articleFromDb);

        //return response entity:
        return modelMapper.map(saved, ArticleResponseDto.class);
    }

    @Override
    public ArticleResponseDto deleteArticleById(long id) {
        Article article = getArticleEntity(id);
        articleRepository.deleteById(id);
        return modelMapper.map(article, ArticleResponseDto.class);
    }

    @Override
    public ArticlePageResponseDto getAllArticles(int pageNo, int pageSize, String sortDir, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.fromString(sortDir), sortBy);

        Page<Article> page = articleRepository.findAll(pageable);

        return ArticlePageResponseDto.builder()
                .results(page.stream().map(article -> modelMapper.map(article, ArticleWithCommentsDto.class)).toList())
                .pageSize(page.getSize())
                .totalPages(page.getTotalPages())
                .pageNo(page.getNumber())
                .totalArticles(page.getTotalElements())
                .isLast(page.isLast())
                .isFirst(page.isFirst())
                .build();
    }



    private Article getArticleEntity(long id) {
        return articleRepository.findById(id).orElseThrow();
        // TODO: 30/09/2023 configure resourceNotFoundException and throw it
    }
}
