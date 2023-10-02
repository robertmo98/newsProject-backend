package edu.robertmo.newsproject.controller;

import edu.robertmo.newsproject.dto.ArticlePageResponseDto;
import edu.robertmo.newsproject.dto.ArticleRequestDto;
import edu.robertmo.newsproject.dto.ArticleResponseDto;
import edu.robertmo.newsproject.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
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

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ArticleResponseDto> updateArticleById(
            @PathVariable long id,
            @RequestBody ArticleRequestDto dto) {
        return ResponseEntity.ok(articleService.updateArticleById(dto, id));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ArticleResponseDto> deleteArticleById(
            @PathVariable long id) {
        return ResponseEntity.ok(articleService.deleteArticleById(id));
    }

    @GetMapping("/page")
    public ResponseEntity<ArticlePageResponseDto> getArticlesPage(
            @RequestParam(value = "pageNo", required = false, defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir,
            @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy
    ) {
        return ResponseEntity.ok(articleService.getAllArticles(pageNo, pageSize, sortDir, sortBy));
    }
}
