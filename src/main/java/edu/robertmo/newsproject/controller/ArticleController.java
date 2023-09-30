package edu.robertmo.newsproject.controller;

import edu.robertmo.newsproject.dto.ArticleRequestDto;
import edu.robertmo.newsproject.dto.ArticleResponseDto;
import edu.robertmo.newsproject.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<ArticleResponseDto> createArticle(
            @RequestBody ArticleRequestDto dto, UriComponentsBuilder uriBuilder) {

        var saved = articleService.createArticle(dto);
        var uri = uriBuilder.path("/api/v1/articles/{id}").buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(uri).body(saved);
    }


    @GetMapping
    public ResponseEntity<List<ArticleResponseDto>> getAllArticles() {
        return ResponseEntity.ok(articleService.getAllArticles());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponseDto> getArticleById(@PathVariable long id) {
        return ResponseEntity.ok(articleService.getArticleById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ArticleResponseDto> deleteArticleById(
            @PathVariable long id) {
        return ResponseEntity.ok(articleService.deleteArticleById(id));
    }

}
