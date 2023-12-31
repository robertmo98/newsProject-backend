package edu.robertmo.newsproject.service;

import edu.robertmo.newsproject.dto.request.ArticleUpdateRequestDto;
import edu.robertmo.newsproject.dto.response.ArticlePageResponseDto;
import edu.robertmo.newsproject.dto.request.ArticleRequestDto;
import edu.robertmo.newsproject.dto.response.ArticleResponseDto;
import edu.robertmo.newsproject.dto.response.ArticleWithCommentsDto;
import edu.robertmo.newsproject.entity.Article;
import edu.robertmo.newsproject.error.ResourceNotFoundException;
import edu.robertmo.newsproject.repository.ArticleRepository;
import edu.robertmo.newsproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    @Override
    public ArticleResponseDto createArticle(ArticleRequestDto dto, Authentication authentication) {
        Article entity = modelMapper.map(dto, Article.class);
        var user = userRepository.findByUsernameIgnoreCase(authentication.getName()).orElseThrow();
        entity.setDate(LocalDate.now());
        entity.setUser(user);
        var saved = articleRepository.save(entity);

        return modelMapper.map(saved, ArticleResponseDto.class);
    }

    @Override
    public List<ArticleResponseDto> getAllArticles(Authentication authentication) {
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
    public ArticleResponseDto updateArticleById(ArticleUpdateRequestDto dto, long id) {
        Article articleFromDb = getArticleEntity(id);

        //update => copy new props from request dto
        articleFromDb.setCategory(dto.getCategory());
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
    @Transactional
    public ArticleResponseDto deleteArticleById(long id) {
        Article article = getArticleEntity(id);
        articleRepository.deleteById(id);
        return modelMapper.map(article, ArticleResponseDto.class);
    }

    @Override
    public ArticlePageResponseDto getAllArticles(
            int pageNo, int pageSize, String sortDir, String sortBy, Authentication authentication) {
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
        return articleRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Article", id));
    }
}
