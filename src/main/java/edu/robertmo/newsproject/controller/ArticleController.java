package edu.robertmo.newsproject.controller;

import edu.robertmo.newsproject.dto.response.ArticlePageResponseDto;
import edu.robertmo.newsproject.dto.request.ArticleRequestDto;
import edu.robertmo.newsproject.dto.response.ArticleResponseDto;
import edu.robertmo.newsproject.service.ArticleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
            @RequestBody @Valid ArticleRequestDto dto, UriComponentsBuilder uriBuilder, Authentication authentication) {

        var saved = articleService.createArticle(dto);
        var uri = uriBuilder.path("/api/v1/articles/{id}").buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(uri).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<ArticleResponseDto>> getAllArticles() {
        return ResponseEntity.ok(articleService.getAllArticles());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponseDto> getArticleById(@NotNull @PathVariable @Valid long id) {
        return ResponseEntity.ok(articleService.getArticleById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ArticleResponseDto> updateArticleById(
            @NotNull @Valid @PathVariable long id,
            @Valid @RequestBody ArticleRequestDto dto,
            Authentication authentication) {
        return ResponseEntity.ok(articleService.updateArticleById(dto, id));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ArticleResponseDto> deleteArticleById(
            @NotNull @Valid @PathVariable long id) {
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
